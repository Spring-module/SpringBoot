package com.wisely.ch9_2.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.wisely.ch9_2.domain.Person;

/**
 * 配置（Spring Boot 会自动初始化 Spring Batch 数据库，并将 csv中的数据导入到数据库中。）：
 * @EnableBatchProcessing 开启批处理的支持
 */
//@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {

	/**
	 * ItemReader 定义：
	 * 1 使用 FlatFileItemReader 读取文件。
	 * 2 使用 FlatFileItemReader 的 setResource 方法设置 csv 文件的路径。
	 * 3 在此处对 cvs 文件的数据和领域模型类做对应映射。
	 */
	@Bean
	public ItemReader<Person> reader() throws Exception {
		FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>(); //1
		reader.setResource(new ClassPathResource("people.csv")); //2
	        reader.setLineMapper(new DefaultLineMapper<Person>() {{ //3
	            setLineTokenizer(new DelimitedLineTokenizer() {{
	                setNames(new String[] { "name","age", "nation" ,"address"});
	            }});
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
	                setTargetType(Person.class);
	            }});
	        }});
	        return reader;
	}
	
	/**
	 * ItemProcessor 定义：
	 * 1 使用我们自己定义的 ItemProcessor 的实现 CsvitemProcessor。
	 * 2 为 processor 指定校验器为 CsvBeanValidator。
	 */
	@Bean
	public ItemProcessor<Person, Person> processor() {
		CsvItemProcessor processor = new CsvItemProcessor(); //1
		processor.setValidator(csvBeanValidator()); //2
		return processor;
	}
	
	
	/**
	 * ItemWriter 定义：
	 * 1 Spring 能让容器中已有的 Bean 以参数的形式注入，Spring Boot 已为我们定义了 dataSource。
	 * 2  我们使用 JDBC 批处理的 JdbcBeatchItemWriter 来写数据到数据库。
	 * 3  在此设置要执行批处理的 SQL 语句。
	 */
	@Bean
	public ItemWriter<Person> writer(DataSource dataSource) {//1
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>(); //2
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		String sql = "insert into person " + "(id,name,age,nation,address) "
				+ "values(hibernate_sequence.nextval, :name, :age, :nation,:address)";
		writer.setSql(sql); //3
		writer.setDataSource(dataSource);
		return writer;
	}

	/**
	 * JobRepository 定义：
	 * jobRepository 的定义需要 dataSource 和 transactionManager，Spring Boot 已为我们自动配置了这两个类，Spring 可通过方法注入已有的 Bean。
	 */
	@Bean
	public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(dataSource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
		jobRepositoryFactoryBean.setDatabaseType("oracle");
		return jobRepositoryFactoryBean.getObject();
	}

	/**
	 * JobLauncher 定义：
	 */
	@Bean
	public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
		return jobLauncher;
	}

	/**
	 * Job 定义：
	 * 1 为 Job 指定 Step。
	 * 2 绑定监听器 csvJobListener。
	 */
	@Bean
	public Job importJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importJob")
				.incrementer(new RunIdIncrementer())
				.flow(s1) //1
				.end()
				.listener(csvJobListener()) //2
				.build();
	}

	/**
	 * Step 定义：
	 * 1 批处理每次提交 65000 条数据。
	 * 2 给 step 绑定 reader。
	 * 3 给 step 绑定 processor。
	 * 4 给 step 绑定 writer。
	 */
	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer,
			ItemProcessor<Person,Person> processor) {
		return stepBuilderFactory
				.get("step1")
				.<Person, Person>chunk(65000) //1
				.reader(reader) //2
				.processor(processor) //3
				.writer(writer) //4
				.build();
	}



	@Bean
	public CsvJobListener csvJobListener() {
		return new CsvJobListener();
	}

	@Bean
	public Validator<Person> csvBeanValidator() {
		return new CsvBeanValidator<Person>();
	}
	

}

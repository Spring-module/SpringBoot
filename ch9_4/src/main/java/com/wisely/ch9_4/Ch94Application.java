package com.wisely.ch9_4;

import static java.lang.System.getProperty;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.mail.Mail;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.scheduling.PollerMetadata;

import com.rometools.rome.feed.synd.SyndEntry;

@SpringBootApplication
public class Ch94Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch94Application.class, args);
	}

	/**
	 * 读取流程
	 * 1 通过 @value 注解自动获得 https://spring.io/blog.atom 的资源。
	 * 2 使用 Fluent API 和 Pollers 配置默认的轮询方式。
	 * 3 FeedEntryessageSource 实际为 feed:inbound-channel-adapter，此处即构造 feed 的入站通道适配器作为数据输入
	 * 4 流程从 from 方法开始。
	 * 5 通过路由方法 route 来选择路由，消息体（payload）的类型为 SyndEntry，作为判断条件的类型为 String，判断的值是通过payload 获得的分类（Caegroy）。
	 * 6 通过不同分类的值转向不同的消息通道，若分类为 releeases，则转向 releasesChanel；若分类为 engineering，则转向 engineeringChanel;若分类为 news，则转向newsChannel。
	 * 7 通过 get 方法获得 IntegrationFlow 实体，配置为 Spring 的 Bean。
	 */
	@Value("https://spring.io/blog.atom") // 1
	Resource resource;

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller() { // 2
		return Pollers.fixedRate(500).get();
	}

	@Bean
	public FeedEntryMessageSource feedMessageSource() throws IOException { //3
		FeedEntryMessageSource messageSource = new FeedEntryMessageSource(resource.getURL(), "news");
		return messageSource;
	}

	@Bean
	public IntegrationFlow myFlow() throws IOException {
		return IntegrationFlows.from(feedMessageSource()) //4
				.<SyndEntry, String> route(payload -> payload.getCategories().get(0).getName(),//5
						mapping -> mapping.channelMapping("releases", "releasesChannel") //6
								.channelMapping("engineering", "engineeringChannel")
								.channelMapping("news", "newsChannel"))

		.get(); // 7
	}

	/**
	 * releases 流程：
	 * 1 从消息通道 releasesChannel 开始获取数据。
	 * 2 使用 transform 方法进行数据转换。payload 类型为 SyndEntry，将其转换为字符串类型，并自定义数据的格式。
	 * 3 用 handle 方法处理 file 的出站适配器。Files类是由Spring Integratin Java DSL 提供的 Fluent API用来构造文件输出的适配器。
	 */
	@Bean
	public IntegrationFlow releasesFlow() {
		return IntegrationFlows.from(MessageChannels.queue("releasesChannel", 10)) //1
				.<SyndEntry, String> transform(
						payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator")) //2
				.handle(Files.outboundAdapter(new File("e:/springblog")) //3
						.fileExistsMode(FileExistsMode.APPEND) //4
						.charset("UTF-8") //5
						.fileNameGenerator(message -> "releases.txt") //6
						.get())
				.get();
	}

	/**
	 * engineering 流程：
	 */
	@Bean
	public IntegrationFlow engineeringFlow() {
		return IntegrationFlows.from(MessageChannels.queue("engineeringChannel", 10))
				.<SyndEntry, String> transform(
						payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
				.handle(Files.outboundAdapter(new File("e:/springblog"))
						.fileExistsMode(FileExistsMode.APPEND)
						.charset("UTF-8")
						.fileNameGenerator(message -> "engineering.txt")
						.get())
				.get();
	}

	/**
	 * news 流程：
	 * 1 通过 enricherHeader 来增加消息头的信息。
	 * 2 邮件发送的相关信息通过 Spring Interation Java DSL提供的Mail 的headers 方法来构造。
	 * 3 使用 handle 方法来定义有邮件发送的出站适配器，使用 Spring Interation Java DSL提供的 Mail.outboundAdapter来构造，这里使用 wisely-man@126.com邮箱向自己发送邮件。
	 */
	@Bean
	public IntegrationFlow newsFlow() {
		return IntegrationFlows.from(MessageChannels.queue("newsChannel", 10))
				.<SyndEntry, String> transform(
						payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
				.enrichHeaders( //1
						Mail.headers()
						.subject("来自Spring的新闻")
						.to("1229647502@qq.com")
						.from("1229647502@qq.com"))
				.handle(Mail.outboundAdapter("smtp.126.com") //2
						.port(25)
						.protocol("smtp")
						.credentials("1229647502@qq.com", "kai#yun%11190511")
						.javaMailProperties(p -> p.put("mail.debug", "false")), e -> e.id("smtpOut"))
				.get();
	}

}

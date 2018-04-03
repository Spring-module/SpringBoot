package com.wisely.ch7_4;

import java.util.concurrent.TimeUnit;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Ch74Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch74Application.class, args);
    }
    
    /**
     * 7.4.2 通过代码的方式配置 servlet 容器
     * 若在当前已有的配置文件内添加类的 Bean 的话，则在 Spring 配置中，注意当前类要声明为 static
     */
    @Component
    public static class CustomServletContainer implements EmbeddedServletContainerCustomizer{
		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.setPort(8888);//1
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
			container.setSessionTimeout(10,TimeUnit.MINUTES);
		}
    }
    
    /**
     * 7.4.2 代码配置 tomcat
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
    factory.setPort(8888);
    factory.setSessionTimeout(10, TimeUnit.MINUTES);
    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
    return factory;
    }
    
    
    /**
     * 7.4.4 http 转向 https
     */
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//      TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//          @Override
//          protected void postProcessContext(Context context) {
//            SecurityConstraint securityConstraint = new SecurityConstraint();
//            securityConstraint.setUserConstraint("CONFIDENTIAL");
//            SecurityCollection collection = new SecurityCollection();
//            collection.addPattern("/*");
//            securityConstraint.addCollection(collection);
//            context.addConstraint(securityConstraint);
//          }
//        };
//      
//      tomcat.addAdditionalTomcatConnectors(httpConnector());
//      return tomcat;
//    }
//    @Bean
//    public Connector httpConnector() {
//      Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//      connector.setScheme("http");
//      connector.setPort(8080);
//      connector.setSecure(false);
//      connector.setRedirectPort(8443);
//      return connector;
//    }
}

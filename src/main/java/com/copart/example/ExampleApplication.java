package com.copart.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class, ThymeleafAutoConfiguration.class})
//Hack to disable mybatis mapper scan

@EnableAspectJAutoProxy
@EnableScheduling
@EnableAsync
public class ExampleApplication extends SpringBootServletInitializer{

	private static final Logger  log = LoggerFactory.getLogger(ExampleApplication.class);
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder)
	    {
	        return applicationBuilder.sources(ExampleApplication.class);
	    }
	
	public static void main(String[] args) 
	{
		
		log.info("************************** BOOTING WEB APPLICATION ********************************");
		SpringApplication.run(ExampleApplication.class, args);
		log.info("************************** BOOTED WEB APPLICATION ********************************");
	}
}

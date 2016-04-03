package com.example;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EntityScan(basePackages = "com/example/model")
@ImportResource("classpath:dbconfig.xml")
public class SpringRestBootApplication {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(SpringRestBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringRestBootApplication.class, args);
	}

}

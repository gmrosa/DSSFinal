package com.mycuteblog;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://mycuteblog.com/h2-database-example-hibernate-spring-boot/
 */
@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(WebApplication.class, args);
	}
}

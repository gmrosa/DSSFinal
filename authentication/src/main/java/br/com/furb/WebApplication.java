package br.com.furb;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(WebApplication.class, args);
	}
}

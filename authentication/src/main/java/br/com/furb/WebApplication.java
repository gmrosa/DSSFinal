package br.com.furb;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

@SpringBootApplication
@EntityScan("com.server.models")
public class WebApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SpringApplication.run(WebApplication.class, args);
	}
}

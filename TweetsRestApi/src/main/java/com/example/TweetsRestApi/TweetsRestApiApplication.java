package com.example.TweetsRestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TweetsRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetsRestApiApplication.class, args);
	}

}

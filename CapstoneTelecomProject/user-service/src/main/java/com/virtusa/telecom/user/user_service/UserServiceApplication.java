package com.virtusa.telecom.user.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.virtusa.telecom.utility.commonlib.AesProperties;
import com.virtusa.telecom.utility.commonlib.HashProperties;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.virtusa.telecom.user.user_service",
	    "com.virtusa.telecom.utility.commonlib"
	})
@EnableConfigurationProperties({AesProperties.class,HashProperties.class})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}

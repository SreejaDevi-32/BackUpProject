package com.virtusa.telecom.plans.plan_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan(basePackages = {"com.virtusa.telecom.plans.plan_service", "com.virtusa.telecom.utility.commonlib" })
@ConfigurationPropertiesScan(basePackages = "com.virtusa.telecom.utility.commonlib")
@EnableJpaAuditing
public class PlanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanServiceApplication.class, args);
	}

}

package com.virtusa.telecom.utility.commonlib;

import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@ConfigurationProperties(prefix ="hash")
public class HashProperties {
	
	@NotBlank
	private String secret;

}

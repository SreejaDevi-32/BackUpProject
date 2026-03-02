package com.virtusa.telecom.plans.plan_service.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class PlanDto {
	private UUID id;
	
	private String name;
	
	private BigDecimal price;
	
	private int validityDays;
	

}
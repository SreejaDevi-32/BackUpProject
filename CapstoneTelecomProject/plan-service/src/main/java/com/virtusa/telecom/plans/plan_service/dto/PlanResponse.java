package com.virtusa.telecom.plans.plan_service.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


public record PlanResponse(
		UUID id,
		String name,
		String type,
		Integer dataLimitGb,
		BigDecimal price,
		List<String> features,
		int validityDays
		) {

}

package com.virtusa.telecom.plans.plan_service.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import com.virtusa.telecom.plans.plan_service.dto.CreatePlanRequest;
import com.virtusa.telecom.plans.plan_service.dto.PlanDto;
import com.virtusa.telecom.plans.plan_service.dto.PlanResponse;
import com.virtusa.telecom.plans.plan_service.systemconstants.PlanType;

public interface PlanService {
	
	public List<PlanResponse> searchPlans(
			PlanType type, BigDecimal minPrice, BigDecimal maxPrice,Integer dataLimit, Pageable pageable);

	public PlanResponse createPlan(CreatePlanRequest request);
	
	public PlanDto getPlanById(UUID id);
	
	
}

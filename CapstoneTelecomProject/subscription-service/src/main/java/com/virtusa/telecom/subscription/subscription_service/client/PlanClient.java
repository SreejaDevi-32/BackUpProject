package com.virtusa.telecom.subscription.subscription_service.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.virtusa.telecom.subscription.subscription_service.dto.PlanDto;

@FeignClient(name = "PLAN-SERVICE")
public interface PlanClient {

    @GetMapping("/api/v1/plans/{id}")
    PlanDto getPlanById(@PathVariable UUID id);
}
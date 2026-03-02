package com.virtusa.telecom.plans.plan_service.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.virtusa.telecom.plans.plan_service.dto.CreatePlanRequest;
import com.virtusa.telecom.plans.plan_service.dto.PlanDto;
import com.virtusa.telecom.plans.plan_service.dto.PlanResponse;
import com.virtusa.telecom.plans.plan_service.entity.Plan;
import com.virtusa.telecom.plans.plan_service.exception.DataNotFoundException;
import com.virtusa.telecom.plans.plan_service.exception.DuplicatePlanException;
import com.virtusa.telecom.plans.plan_service.repository.PlanRepository;
import com.virtusa.telecom.plans.plan_service.systemconstants.PlanType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepo;

    @Override
    public List<PlanResponse> searchPlans(PlanType type,
                                          BigDecimal minPrice,
                                          BigDecimal maxPrice,
                                          Integer dataLimit,
                                          Pageable pageable) {

        log.info("Searching plans with filters -> type={}, minPrice={}, maxPrice={}, dataLimit={}, page={}, size={}",
                type, minPrice, maxPrice, dataLimit,
                pageable.getPageNumber(), pageable.getPageSize());

        Page<Plan> pageResult =
                planRepo.searchPlans(type, minPrice, maxPrice, dataLimit, pageable);

        if (pageResult.hasContent()) {

            log.info("Found {} plans matching criteria", pageResult.getTotalElements());

            return pageResult
                    .map(this::mapToDto)
                    .getContent();
        }

        log.warn("No plans found for given search criteria");

        throw new DataNotFoundException("Data Not Found");
    }

    @Override
    public PlanResponse createPlan(CreatePlanRequest request) {

        log.info("Creating plan with name={}, type={}, price={}",
                request.getName(),
                request.getType(),
                request.getPrice());

        if (planRepo.existsByName(request.getName())) {

            log.warn("Duplicate plan creation attempt for name={}", request.getName());

            throw new DuplicatePlanException(
                    "Plan with name '" + request.getName() + "' already exists");
        }

        Plan plan = new Plan();
        BeanUtils.copyProperties(request, plan);

        Plan savedPlan = planRepo.save(plan);

        log.info("Plan created successfully with id={}", savedPlan.getId());

        return mapToDto(savedPlan);
    }

    @Override
    public PlanDto getPlanById(UUID id) {

        log.debug("Fetching plan by id={}", id);

        Plan existingPlan = planRepo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Plan not found with id={}", id);
                    return new DataNotFoundException("Data Not Found");
                });

        PlanDto planDto = new PlanDto();
        BeanUtils.copyProperties(existingPlan, planDto);

        log.info("Plan fetched successfully with id={}", id);

        return planDto;
    }

    private PlanResponse mapToDto(Plan plan) {
        return new PlanResponse(
                plan.getId(),
                plan.getName(),
                plan.getType().toString(),
                plan.getDataLimitGb(),
                plan.getPrice(),
                plan.getFeatures(),
                plan.getValidityDays()
        );
    }
}
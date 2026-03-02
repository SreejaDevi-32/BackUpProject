package com.virtusa.telecom.plans.plan_service.dto;

import java.math.BigDecimal;
import java.util.List;

import com.virtusa.telecom.plans.plan_service.systemconstants.PlanType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreatePlanRequest {

	@NotBlank(message = "Plan name is required")
    private String name;
	
	@NotNull(message = "Plan type is required")
    private PlanType type;
	
	 @NotNull(message = "Price is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;
	 
	 @NotNull(message = "Data limit is required")
	 @Min(value = 1, message = "Data limit must be at least 1 GB")
    private Integer dataLimitGb;
	 
	@NotEmpty(message = "Features cannot be empty")
    private List<@NotBlank String> features;
	
    private Boolean active=true;
    
    private int validityDays=0;

    // getters & setters
}

package com.virtusa.telecom.plans.plan_service.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.virtusa.telecom.plans.plan_service.systemconstants.PlanType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(
	    name = "plans",
	    uniqueConstraints = {
	        @UniqueConstraint(columnNames = "name")
	    }
	)
@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor @Builder
public class Plan extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PlanType type; // PREPAID, POSTPAID

    private Integer dataLimitGb;

    private BigDecimal price;

    private List<String> features;

    private boolean active;
    
    private int validityDays;

}
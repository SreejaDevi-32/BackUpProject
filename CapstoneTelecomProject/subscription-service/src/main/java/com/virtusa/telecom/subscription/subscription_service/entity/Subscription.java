package com.virtusa.telecom.subscription.subscription_service.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import com.virtusa.telecom.subscription.subscription_service.systemconstants.SubscriptionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="subscriptions",
	uniqueConstraints = {
			@UniqueConstraint(
					name = "uk_user_active_subscription",
					columnNames = {"user_id","status"}
					)
	}
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Subscription extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name ="user_id",nullable = false)
	private UUID userId;
	
	@Column(name="plan_id",nullable = false)
	private UUID planId;
	
	@Column(name="plan_name",nullable = false)
	private String planName;
	
	@Column(name ="price",nullable = false)
	private BigDecimal price;
	
	@Column(name ="start_date",nullable = false)
	private LocalDate startDate;
	
	
	@Column(name ="end_date",nullable = false)
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SubscriptionStatus status;
	
	

}

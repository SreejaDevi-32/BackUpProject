package com.virtusa.telecom.plans.plan_service.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.virtusa.telecom.plans.plan_service.dto.PlanDto;
import com.virtusa.telecom.plans.plan_service.entity.Plan;
import com.virtusa.telecom.plans.plan_service.systemconstants.PlanType;

@Repository
public interface PlanRepository extends JpaRepository<Plan,UUID>{
	
	
	@Query("""
			SELECT p FROM Plan p
			WHERE p.active = true
  AND (:type IS NULL OR p.type = :type)
  AND (:minPrice IS NULL OR p.price >= :minPrice)
  AND (:maxPrice IS NULL OR p.price <= :maxPrice)
  AND (:dataLimit IS NULL OR p.dataLimitGb = :dataLimit)

			""")
	Page<Plan> searchPlans(@Param("type") PlanType type,
			@Param("minPrice") BigDecimal minPrice,
			@Param("maxPrice") BigDecimal maxPrice,
			@Param("dataLimit")Integer dataLimit,
			Pageable pageable
			);
	
	boolean existsByName(String name);
	
	Optional<Plan> getPlanById(UUID id);

}

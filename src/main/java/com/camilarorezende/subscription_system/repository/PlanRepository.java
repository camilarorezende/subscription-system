package com.camilarorezende.subscription_system.repository;

import com.camilarorezende.subscription_system.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}

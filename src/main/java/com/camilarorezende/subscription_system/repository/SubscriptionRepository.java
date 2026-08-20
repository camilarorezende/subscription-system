package com.camilarorezende.subscription_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camilarorezende.subscription_system.models.Status;
import com.camilarorezende.subscription_system.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

    boolean existsByPlanIdAndStatus(Long planId, Status status);

    List<Subscription> findByUserIdAndStatus(Long userId, Status status);

    boolean existsByUserIdAndPlanIdAndStatus(Long userId, Long planId, Status status);

    boolean existsByUserIdAndStatus(Long userId, Status status);
}

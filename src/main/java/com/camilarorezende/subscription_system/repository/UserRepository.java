package com.camilarorezende.subscription_system.repository;

import com.camilarorezende.subscription_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

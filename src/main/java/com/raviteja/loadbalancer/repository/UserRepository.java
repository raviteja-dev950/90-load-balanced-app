package com.raviteja.loadbalancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.raviteja.loadbalancer.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
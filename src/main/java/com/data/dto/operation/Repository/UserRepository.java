package com.data.dto.operation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.dto.operation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

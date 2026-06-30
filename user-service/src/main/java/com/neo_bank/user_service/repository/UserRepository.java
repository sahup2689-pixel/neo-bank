package com.neo_bank.user_service.repository;

import com.neo_bank.user_service.entity.User;
import com.neo_bank.user_service.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


 // EXISTENCE CHECKS

 boolean existsByEmail(String email);

 boolean existsByMobileNumber(String mobileNumber);

 Optional<User> findByEmail(String email);

 Optional<User> findByMobileNumber(String mobileNumber);

 List<User> findByStatus(UserStatus status);
}
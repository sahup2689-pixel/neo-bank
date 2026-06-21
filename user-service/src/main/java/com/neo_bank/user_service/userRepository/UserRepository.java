package com.neo_bank.user_service.userRepository;


import com.neo_bank.user_service.userEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail(String Email);

}

package com.neo_bank.user_service.userservice;

import com.neo_bank.user_service.userDTO.UserRegistrationRequest;
import com.neo_bank.user_service.userEntity.UserEntity;
import com.neo_bank.user_service.userRepository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

private final UserRepository userRepository;
//
public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//
//    public String register(UserRegistrationRequest userRegistrationRequest){
//        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())){
//            return "Email Already Exists";
//        }
//
//        UserEntity user = new UserEntity();
//        user.setFullname(userRegistrationRequest.getFullName());
//        user.setMobileNumber(userRegistrationRequest.getMobileNumber());
//        user.setEmail(userRegistrationRequest.getEmail());
//        user.setPassword(userRegistrationRequest.getPassword());
//
//        userRepository.save(user);
//        return "User Register Successfully";
//    }
public String register(UserRegistrationRequest userRegistrationRequest){

    UserEntity user = new UserEntity();

    user.setFullname(userRegistrationRequest.getFullName());
    user.setMobileNumber(userRegistrationRequest.getMobileNumber());
    user.setEmail(userRegistrationRequest.getEmail());
    user.setPassword(userRegistrationRequest.getPassword());

    userRepository.save(user);

    return "User Register Successfully";
}
}

package com.neo_bank.user_service.userservice;

import com.neo_bank.user_service.security.JwtUtil;
import com.neo_bank.user_service.userDTO.LoginRequest;
import com.neo_bank.user_service.userDTO.UserRegistrationRequest;
import com.neo_bank.user_service.userEntity.UserEntity;
import com.neo_bank.user_service.userRepository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(UserRegistrationRequest userRegistrationRequest){

    UserEntity user = new UserEntity();

    user.setFullname(userRegistrationRequest.getFullName());
    user.setMobileNumber(userRegistrationRequest.getMobileNumber());
    user.setEmail(userRegistrationRequest.getEmail());
    //user.setPassword(userRegistrationRequest.getPassword());
user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
    userRepository.save(user);

    return "User Register Successfully";
}

public String login(LoginRequest request){
        UserEntity user = userRepository.findByEmail(request.getEmail());
        if (user == null){
            return "User Not Found";
        }
        boolean matched = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!matched){
            return "Invalid User";
        }
     return JwtUtil.generateToken(user.getEmail());
}

}

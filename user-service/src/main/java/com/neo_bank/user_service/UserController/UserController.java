package com.neo_bank.user_service.UserController;

import com.neo_bank.user_service.userDTO.UserRegistrationRequest;
import com.neo_bank.user_service.userservice.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegistrationRequest request){
        return userService.register(request);
    }
}

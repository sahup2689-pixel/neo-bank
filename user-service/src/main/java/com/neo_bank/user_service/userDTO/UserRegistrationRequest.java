package com.neo_bank.user_service.userDTO;

public class UserRegistrationRequest {
    private String fullName;
    private String Email;
    private Long mobileNumber;
    private String password;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String fullName, String email, Long mobileNumber, String password) {
        this.fullName = fullName;
        Email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

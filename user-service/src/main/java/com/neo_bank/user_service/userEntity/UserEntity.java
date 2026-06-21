package com.neo_bank.user_service.userEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long user_id;
    private String fullName;
    private String Email;
   private Long mobileNumber;
   private String password;

    public UserEntity() {
    }

    public UserEntity(Long user_id, String fullname, String email, Long mobileNumber, String password) {
        this.user_id = user_id;
        this.fullName = fullname;
        Email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
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

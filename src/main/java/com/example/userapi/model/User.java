package com.example.userapi.model;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;


@ApiModel(description = "User model")

public class User {

    public Integer id;
    @ApiModelProperty(notes = "First name", example = "")
    public String firstName;
    @ApiModelProperty(notes = "Last name", example = "")
    public String lastName;

    @ApiModelProperty(notes = "Email address", example = "")
    public String email;
    @ApiModelProperty(notes = "Active status", example = "1 for active 0 deactivated")
    public int status;


    public User() {
    }

    public User(Integer id, String firstName,String lastName, String email, int status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
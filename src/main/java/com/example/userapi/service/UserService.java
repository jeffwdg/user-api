package com.example.userapi.service;

import com.example.userapi.model.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    User addUser(User user);

    User getUserById(Integer userId);

    User editUser(Integer userId, User user);

    boolean deleteUserById(Integer userId);

}

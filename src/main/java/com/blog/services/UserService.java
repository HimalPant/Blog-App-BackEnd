package com.blog.services;

import com.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    // For creating a new user
    UserDto createUser(UserDto user);

    // For updating a user
    UserDto updateUser(UserDto user, Integer userId);

    // for getting/fetching by id
    UserDto getUserById(Integer userId);

    // for getting/fetching all users
    List<UserDto> getAllUsers();

    // for deleting user by id
    void deleteUser(Integer userId);
}

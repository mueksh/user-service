package com.ecommerce.user.service;

import com.ecommerce.user.dto.request.UserRequest;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Creating new user
    UserResponse createUser(UserRequest userRequest);

    //Finding User by UserEmail
    UserResponse getUserByEmail(String mail);

    //Fetching all user
    List<UserResponse> getAllUsers();

    //Updating User
    UserResponse updateUser(String mail,UserRequest userRequest);

    //Deleting user
    String deleteUser(String mail);
}

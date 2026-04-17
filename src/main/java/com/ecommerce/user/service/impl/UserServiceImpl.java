package com.ecommerce.user.service.impl;

import com.ecommerce.user.Mapper.AddressMapper;
import com.ecommerce.user.Mapper.UserMapper;
import com.ecommerce.user.dto.request.AddressRequest;
import com.ecommerce.user.dto.request.UserRequest;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.exception.EmailAlreadyExistsException;
import com.ecommerce.user.exception.UserNotFoundException;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    ObjectMapper objectMapper;

    // Creating New User
    @Override
    public UserResponse createUser(UserRequest userRequest) {

        log.info("Checking user already present with mail :" +userRequest.getEmail());
        // Check email uniqueness
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            log.error("User already registered with mail :" +userRequest.getEmail());
            throw new EmailAlreadyExistsException("Email already exists :"+userRequest.getEmail());
        }

        // Convert DTO → Entity
        User user = UserMapper.toEntity(userRequest);
        log.warn("User successfully converted from UserRequest to UserEntity :" +user);

        log.info("Saving User into DB "+user);
        User savedUser=userRepository.save(user);

        return UserMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse getUserByEmail(String mail) {
        if (mail==null){
            log.warn("Mail is Null, please provide valid mail");
            return null;
        }

        User user
                =userRepository.findByEmail(mail).orElseThrow(
                        ()-> new UserNotFoundException("User not found with mail : "+ mail));
        return UserMapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> userList=userRepository.findAll();

        log.info("Converting all user into userResponse by one"+userList);

        return UserMapper.toUserResponseList(userList);

    }

    //updating user by its mail
    @Override
    public UserResponse updateUser(String mail,UserRequest userRequest) {

        if (mail==null){
            log.warn("Mail is Null, please provide valid mail");
            return null;
        }

        log.info("Finding user by mail: "+mail);
        User user=userRepository
                .findByEmail(mail)
                .orElseThrow(
                        ()-> new UserNotFoundException("User not found with mail : "+ mail));
        log.info("User found successfully with mail: "+mail);

        List<AddressRequest> addressRequestList
                =userRequest.getAddresses();


        List<Address> addressList
                =AddressMapper.toAddressEntityList(addressRequestList);

        log.info("Setting all the user updated parameters");
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setRole(userRequest.getRole());
        user.setCreatedAt(user.getCreatedAt());
        user.setAddresses(addressList);

        log.info("Saving User into DB ");
        User savedUser=userRepository.save(user);

        return UserMapper.toUserResponse(savedUser);
    }

    @Override
    public String deleteUser(String mail) {
        if (mail==null){
            log.warn("Mail is Null, please provide valid mail");
            return null;
        }
        log.info("Finding user by mail: "+mail);
        User user=userRepository
                .findByEmail(mail)
                .orElseThrow(
                        ()-> new UserNotFoundException("User not found with mail : "+ mail));
        log.info("User found successfully with mail: "+mail);

        userRepository.delete(user);

        return "User deleted successfully with mail: "+mail;

    }
}

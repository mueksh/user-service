package com.ecommerce.user.controller;

import com.ecommerce.user.dto.request.UserRequest;
import com.ecommerce.user.dto.response.UserResponse;
import com.ecommerce.user.model.User;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/e-commerce/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    public final UserService userService;

    //creating User
    @PostMapping("")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest){

        log.info("Creating new User: "+userRequest);
        UserResponse userResponse=userService.createUser(userRequest);

        log.info("User Created Successfully: "+userResponse);

        return ResponseEntity.ok(userResponse);
    }

    //get user by user email
    @GetMapping("/{mail}")
    public ResponseEntity<UserResponse> getUserByMail(@PathVariable("mail") String mail){

        log.info("Fetching User the by its mail :" +mail);
        UserResponse userResponse=userService.getUserByEmail(mail);

        log.info("User Found with this mail : " + mail + "\n User : "+ userResponse);

        return ResponseEntity.ok(userResponse);

    }

    //get all users
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getallUsers(){

        log.info("Fetching all user from DB");
       List<UserResponse> userResponse=userService.getAllUsers();

        log.info("All user fetched successfully "+ userResponse);

        return ResponseEntity.ok(userResponse);
    }


    //Updating user with mail
    @PostMapping("/update/{mail}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("mail") String mail,
                                                   @RequestBody UserRequest userRequest){

        log.info("Updating user with mail "+mail);
        UserResponse userResponse=userService.updateUser(mail,userRequest);

        log.info("User updated Successfully with mail "+mail);

        return ResponseEntity.ok(userResponse);

    }

    //Deleting user
    @DeleteMapping("/delete/{mail}")
    public ResponseEntity<String> deleteUser(@PathVariable("mail") String mail){

        log.info("Deleteing user with mail "+mail);
        String response=userService.deleteUser(mail);

        log.info("User delete successfully with mail: "+mail);

        return ResponseEntity.ok(response);

    }

}

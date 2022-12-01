package com.blog.controller;

import com.blog.payloads.APIResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    // Post-Create user
    @PostMapping("/users/")
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto){
        UserDto dto = this.userService.createUser(userDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    
    // get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable ("id") Integer id){
        UserDto userById = this.userService.getUserById(id);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    // get all users
    @GetMapping("/users/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    // update user
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@Validated @RequestBody UserDto userDto,@PathVariable Integer id){
        UserDto updatedUser = this.userService.updateUser(userDto, id);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    // delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable ("id") Integer id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>(new APIResponse("User Deleted Successfully", true),HttpStatus.OK);
    }
}

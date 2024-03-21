package com.santanna.picpaydesafio.controller;

import com.santanna.picpaydesafio.dto.UserDTO;
import com.santanna.picpaydesafio.domain.user.User;
import com.santanna.picpaydesafio.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServcie userServcie;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User newUser = userServcie.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userServcie.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}

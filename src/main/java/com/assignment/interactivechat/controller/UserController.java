package com.assignment.interactivechat.controller;


import com.assignment.interactivechat.dto.UserData;
import com.assignment.interactivechat.exception.ApiException;
import com.assignment.interactivechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<String> addUser(UserData userData) throws ApiException {
        String createdUUID = userService.createUser(userData);
        return new ResponseEntity<>(createdUUID, HttpStatus.OK);
    }
}

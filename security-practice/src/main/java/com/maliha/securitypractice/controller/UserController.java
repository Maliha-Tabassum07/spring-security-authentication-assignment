package com.maliha.securitypractice.controller;

import com.maliha.securitypractice.model.UserDTO;
import com.maliha.securitypractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> register (@RequestBody UserDTO userDto) throws Exception {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }


}

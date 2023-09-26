package com.grumvalski.yogaLifebackend.controller;


import com.grumvalski.yogaLifebackend.dto.UserLogin;
import com.grumvalski.yogaLifebackend.dto.UserRegistration;

import com.grumvalski.yogaLifebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.security.GeneralSecurityException;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/yogaLife/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/registration")
    public void registrationUser(@RequestBody UserRegistration registration) throws GeneralSecurityException, IOException {
        service.registrationUser(registration);

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin userData, HttpServletResponse response) throws Exception {
        String jwt=service.userLogin(userData);
        System.out.println("JWT: "+jwt);
        response.setHeader("Authorization", "Bearer "+jwt);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}

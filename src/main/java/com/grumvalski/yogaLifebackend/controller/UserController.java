package com.grumvalski.yogaLifebackend.controller;


import com.grumvalski.yogaLifebackend.dto.UserLogin;
import com.grumvalski.yogaLifebackend.dto.UserRegistration;

import com.grumvalski.yogaLifebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/yogaLife")
@CrossOrigin(origins ="http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/registration")
    public void registrationUser(@RequestBody UserRegistration registration) {
        service.registrationUser(registration);

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin userData){
         return service.userLogin(userData);

    }
}

package com.grumvalski.yogaLifebackend.service;

import com.grumvalski.yogaLifebackend.dto.UserLogin;
import com.grumvalski.yogaLifebackend.dto.UserRegistration;
import com.grumvalski.yogaLifebackend.entity.User;

import com.grumvalski.yogaLifebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;



    public void registrationUser(UserRegistration registration){
       User user= new User();
       user.setName(registration.getName());
       user.setLastName(registration.getLastName());
       user.setEmail(registration.getEmail());
       user.setPassword(registration.getPassword());

       repository.save(user);
    }

    public ResponseEntity<?> userLogin(UserLogin login) {
//        User user =repository.findByEmail(login.getEmail());
//
//        if(user.getPassword().equals(login.getPassword())){
//            return ResponseEntity.ok(user);
//        }else{
//            return (ResponseEntity<?>) ResponseEntity.notFound();
//
//        }
        return null;
    }


}

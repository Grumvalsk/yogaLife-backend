package com.grumvalski.yogaLifebackend.service;

import com.grumvalski.yogaLifebackend.dto.UserLogin;
import com.grumvalski.yogaLifebackend.dto.UserRegistration;
import com.grumvalski.yogaLifebackend.model.User;
import com.grumvalski.yogaLifebackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private final UserRepository repository;


    public void registrationUser(UserRegistration registration){
        User user = User.builder()
                .name(registration.getName())
                .lastName(registration.getLastName())
                .email(registration.getEmail())
                .phone(registration.getPhone())
                .password(registration.getPassword())
                .confirmPassword(registration.getConfirmPassword())
                .build();

        repository.save(user);
                log.info("registered user");
    }

    public ResponseEntity<?> userLogin(UserLogin login) {
        User user =repository.findByEmail(login.getEmail());

        if(user.getPassword().equals(login.getPassword())){
            return ResponseEntity.ok(user);
        }else{
            return (ResponseEntity<?>) ResponseEntity.notFound();

        }
    }


}

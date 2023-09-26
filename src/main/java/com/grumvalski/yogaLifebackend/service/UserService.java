package com.grumvalski.yogaLifebackend.service;

import com.grumvalski.yogaLifebackend.dto.UserLogin;
import com.grumvalski.yogaLifebackend.dto.UserRegistration;
import com.grumvalski.yogaLifebackend.entity.User;

import com.grumvalski.yogaLifebackend.repository.UserRepository;
import com.grumvalski.yogaLifebackend.security.JwtCreator;
import com.grumvalski.yogaLifebackend.utils.CrypLocal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;




    public void registrationUser(UserRegistration registration) throws GeneralSecurityException, IOException {
       User user= new User();
       user.setName(registration.getName());
       user.setLastName(registration.getLastName());
       user.setEmail(registration.getEmail());
       user.setPhone(registration.getPhone());
       String pw=registration.getPassword();
       if(pw.length()>= 6 ){
           byte[] stringa=pw.getBytes();
           byte[] value= CrypLocal.getInstance().encrypt(stringa);
           String password=new String(value);
           user.setPassword(password);
       }
       repository.save(user);
    }

    public String userLogin(UserLogin login) throws Exception {
       Optional<User> value =repository.findById(login.getEmail());
       User user=null;
       if(value.isPresent())
            user= value.get();
       String pw=CrypLocal.getInstance().decrypt(user.getPassword());
       if(login.getPassword().equals(pw)){
           String jwt = JwtCreator.createJwt(login.getEmail(),"YogaLife.com" );
           return jwt;
       }else{
       throw new Exception("Password non valida");
   }

    }


}

package com.grumvalski.yogaLifebackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("User")
public class User {


    private String name;
    private String lastName;
    @Id
    private String email;

    private String phone;
    private String password;

    private List<Event> listaCorsi;


    public User() {
    }

    public User(String name, String lastName, String email, String phone, String password, List listaCorsi) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.listaCorsi= listaCorsi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getListaCorsi() {
        return listaCorsi;
    }

    public void setListaCorsi(List<Event> listaCorsi) {
        this.listaCorsi = listaCorsi;
    }
}



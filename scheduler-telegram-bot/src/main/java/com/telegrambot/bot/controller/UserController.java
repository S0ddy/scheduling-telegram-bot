package com.telegrambot.bot.controller;

import com.telegrambot.bot.entity.User;
import com.telegrambot.bot.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
public class UserController {
//    @Autowired
//    UserRepository userRepository;
//
//    @GetMapping(path = "/get")
//    public void getRequest() {
//
//        method();
//
//    }
//    @Transactional
//    public void method() {
//        User user = new User("alex");
//        userRepository.save(user);
//        List<User> users = (List<User>) userRepository.findAll();
//        System.out.println(users);
//    }
}

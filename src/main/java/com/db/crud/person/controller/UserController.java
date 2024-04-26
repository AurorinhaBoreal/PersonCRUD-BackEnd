package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
    
    Logger logger = Logger.getLogger(UserController.class.getName());

    
    @GetMapping("/list")
    public void listUsers() {

    }

    @PostMapping("/create")
    public void createUser(@RequestBody String JSON) {
        logger.log(Level.FINER, "O Corpo do usuário: "+JSON);
    }
    
}

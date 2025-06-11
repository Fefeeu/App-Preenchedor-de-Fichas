package com.rpp.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AccessController {

    public String login(){
        return "login to user";
    }

    public String signup(){
        return "signup user";
    }

    public String verifyAccess(){
        return "verify user access";
    }
}

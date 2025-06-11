package com.rpp.api.service;

import org.springframework.stereotype.Service;

@Service
public class FirstService {
    public String firstFunction(String name){
        return "Hello world " + name;
    }
}

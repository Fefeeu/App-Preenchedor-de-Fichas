package com.rpp.api.services;

import org.springframework.stereotype.Service;

@Service
public class FirstService {
    public String firstFunction(String name){
        return "Hello world " + name;
    }
}

package com.rpp.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    // Get user
    @GetMapping("/{id}")
    public String getUser(@PathVariable String id){
        return "Get user by id: " + id;
    }

    // Update user
    @PutMapping("/update/{id}")
    public String update(@PathVariable String id, @RequestBody Sheet body){
        return "Update user by id: " + id;
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id, @RequestBody Sheet body){
        return "Delete user by id: " + id;
    }
}

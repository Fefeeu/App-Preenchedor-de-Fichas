package com.rpp.api.controller;

import com.rpp.api.domain.Sheet;
import com.rpp.api.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    // Get user
    @GetMapping("/{id}")
    public String getUser(@PathVariable String id){
        return "Get user by id: " + id;
    }

    // Create user
    @PostMapping("/create")
    public String rootPost(@RequestBody User body) {
        if(body.getEmail() == null){
            body.setEmail("emailpadrao.com");
        }
        return "Hello, " + body.getName() + ". Your email is " + body.getEmail() + ".";
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

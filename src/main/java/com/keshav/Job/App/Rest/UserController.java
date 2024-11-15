package com.keshav.Job.App.Rest;

import com.keshav.Job.App.Rest.model.Users;
import com.keshav.Job.App.Rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    // Step-1 // To register user in DB (Controller)
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println(user);
        return service.verify(user);
    }
}

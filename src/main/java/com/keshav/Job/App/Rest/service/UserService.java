package com.keshav.Job.App.Rest.service;

import com.keshav.Job.App.Rest.model.UserPrincipal;
import com.keshav.Job.App.Rest.model.Users;
import com.keshav.Job.App.Rest.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// To register user in DB (Service)
@Service
public class UserService {
    @Autowired
    // Step-1 To BYcrpt the password
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12) ; // Strength is round , how many times our hash will be converts
    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword())); // User when giving password take the password encrypt it and set iy.
        return repo.save(user); // To the User
    }

    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        return "Login Failed";

    }
}
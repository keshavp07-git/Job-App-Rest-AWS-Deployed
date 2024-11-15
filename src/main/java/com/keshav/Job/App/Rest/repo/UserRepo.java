package com.keshav.Job.App.Rest.repo;


import com.keshav.Job.App.Rest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);
}

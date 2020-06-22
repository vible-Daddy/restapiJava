package com.daeyun.controller;

import com.daeyun.entity.User;
import com.daeyun.repository.UserRepository;
import com.daeyun.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //get all users
    @GetMapping
    public List<User> getAllusers(){
        return this.userRepository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable (value="id") long userId){
        return this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with id : "+userId));
    }
    //create user
    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        return this.userRepository.save(user);
    }
    //update user
    @PutMapping("/{id}")
    public User updateUser(@Valid @RequestBody User user,@PathVariable(value="id") long userId){
        User existing = this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id :"+userId));
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return this.userRepository.save(existing);
    }
    //delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value="id") long userId){
        User existing = this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id :"+userId));
        this.userRepository.delete(existing);
        return ResponseEntity.ok().build();
    }
}

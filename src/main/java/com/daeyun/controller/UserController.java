package com.daeyun.controller;

import com.daeyun.entity.User;
import com.daeyun.exception.ParameterMissedException;
import com.daeyun.repository.UserRepository;
import com.daeyun.response.ApiResponse;
import com.daeyun.response.ApiResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //get all users
    @GetMapping
    public Mono<ApiResponse> getAllusers(){
        ApiResponseUtils response = new ApiResponseUtils();
        try{
//            List<User> users = this.userRepository.findAll();
//            response.put("users", Flux.just(users));
            return response.getMono();
        }catch (Exception e){
            e.printStackTrace();
            response.setException(new ParameterMissedException());
            return response.getMono();
        }
    }

//    //get user by id
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable (value="id") long userId){
//        return this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with id : "+userId));
//    }
//    //create user
//    @PostMapping
//    public User createUser(@Valid @RequestBody User user){
//        return this.userRepository.save(user);
//    }
//    //update user
//    @PutMapping("/{id}")
//    public User updateUser(@Valid @RequestBody User user,@PathVariable(value="id") long userId){
//        User existing = this.userRepository.findById(userId)
//                .orElseThrow(()->new ResourceNotFoundException("User not found with id :"+userId));
//        existing.setName(user.getName());
//        existing.setEmail(user.getEmail());
//        return this.userRepository.save(existing);
//    }
//    //delete user by id
//    @DeleteMapping("/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable(value="id") long userId){
//        User existing = this.userRepository.findById(userId)
//                .orElseThrow(()->new ResourceNotFoundException("User not found with id :"+userId));
//        this.userRepository.delete(existing);
//        return ResponseEntity.ok().build();
//    }
}

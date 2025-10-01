package com.vishal.controller;

import com.vishal.model.User;
import com.vishal.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/users")
    public User createUser(@RequestBody @Valid User user){
        return userRepository.save(user);
    }

    @GetMapping("/api/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    //Get the user details by id
    @GetMapping("/api/users/{id}")
    public User getUserById( @PathVariable("id") long id) throws Exception {
        Optional<User> otp = userRepository.findById(id);
        if(otp.isPresent()){
            return otp.get();
        }else{
            throw new Exception("user not found");
        }
    }

    //Update the user details
    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) throws Exception {
        Optional<User> otp = userRepository.findById(id);
        if(otp.isEmpty()){
            throw new Exception("User not found");
        }
        User existingUser = otp.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setMobile(user.getMobile());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @DeleteMapping("/api/users/{id}")
    public  String deleteUserById(@PathVariable long id) throws Exception {
        Optional<User> otp = userRepository.findById(id);
        if(otp.isEmpty()){
            throw new Exception("User is not existing with the ID :" +id);
        }
        userRepository.deleteById(otp.get().getId());
        return "User deleted successfully";
    }
}

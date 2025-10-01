package com.vishal.controller;

import com.vishal.exception.UserException;
import com.vishal.model.User;
import com.vishal.repository.UserRepository;
import com.vishal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    private final UserRepository userRepository;

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //Get the user details by id
    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById( @PathVariable("id") long id) throws Exception {
        User getUserById = userService.getUserById(id);
        return new ResponseEntity<>(getUserById, HttpStatus.OK);
    }

    //Update the user details
    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) throws Exception {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/users/{id}")
    public  ResponseEntity<String> deleteUserById(@PathVariable long id) throws Exception {
        userService.deleteUserById(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.ACCEPTED);
    }
}

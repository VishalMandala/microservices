package com.vishal.service;

import com.vishal.exception.UserException;
import com.vishal.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User> getAllUsers();
    void deleteUserById(Long id) throws UserException;
    User updateUser(Long id, User user) throws UserException;

}

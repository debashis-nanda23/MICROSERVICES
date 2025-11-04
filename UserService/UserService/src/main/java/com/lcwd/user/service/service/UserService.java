package com.lcwd.user.service.service;

import java.util.List;

import com.lcwd.user.service.entites.User;

public interface UserService {
	
    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);
    User deleteUser(String userId);
    User updateUser(User user);
}

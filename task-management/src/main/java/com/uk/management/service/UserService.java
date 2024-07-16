package com.uk.management.service;

import com.uk.management.model.User;


public interface UserService {

    User save(User user);

    public User findByUsername(String username);
}

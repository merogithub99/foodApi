package com.sushant.service;

import com.sushant.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws  Exception;
    public User findUserByEmail(String email) throws  Exception;
}

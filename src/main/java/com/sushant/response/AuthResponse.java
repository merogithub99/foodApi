package com.sushant.response;

import com.sushant.model.USER_ROLE;
import com.sushant.model.User;
import lombok.Data;

@Data
public class AuthResponse {
    private  String jwt;
    private  String message;
    private USER_ROLE role;
}

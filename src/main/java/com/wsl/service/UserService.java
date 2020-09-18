package com.wsl.service;

import com.wsl.po.User;

public interface UserService {
    User checkUser(String name,String password);
}

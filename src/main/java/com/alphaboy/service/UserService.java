package com.alphaboy.service;

import com.alphaboy.pojo.User;

/**
 * @author yaojun
 * @create 2020-12-12 17:36
 */
public interface UserService {
    User findByName(String name);
}
package com.tensquare.service;

import com.tensquare.model.User;

public interface UserService {
    void sendSms(String mobile);

    void add(User user);
}

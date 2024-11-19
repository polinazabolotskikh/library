package com.example.library.service;

import com.example.library.model.db.entity.User;
import com.example.library.model.dto.request.UserInfoRequest;
import com.example.library.model.dto.response.UserInfoResponse;


public interface UserService {
    UserInfoResponse createUser(UserInfoRequest request);

    UserInfoResponse getUser(Long id);

    UserInfoResponse updateUser(Long id, UserInfoRequest request);

    User getUserDb(Long id);

    void deleteUser(Long id);

    User findByLogin(String login);

    Integer getUserType(String login);

    UserInfoRequest convertUser(User s);
}

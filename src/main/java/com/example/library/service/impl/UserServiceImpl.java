package com.example.library.service.impl;

import com.example.library.exceptions.CustomException;
import com.example.library.model.db.entity.User;
import com.example.library.model.db.repository.UserRepo;
import com.example.library.model.dto.request.UserInfoRequest;
import com.example.library.model.dto.response.UserInfoResponse;
import com.example.library.model.enums.Status;
import com.example.library.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ObjectMapper mapper;

    @Override
    public UserInfoResponse createUser(UserInfoRequest request) {
        request.encryptPassword();
        User user = mapper.convertValue(request, User.class);
        user.setStatus(Status.CREATED);
        user.setCreatedAt(LocalDateTime.now());
        user = userRepo.save(user);
        return mapper.convertValue(user, UserInfoResponse.class);
    }

    @Override
    public UserInfoResponse getUser(Long id) {
        return mapper.convertValue(getUserDb(id), UserInfoResponse.class);
    }

    @Override
    public UserInfoResponse updateUser(Long id, UserInfoRequest request) {
        User user = getUserDb(id);
        if (user.getId() != null) {
            user.setLogin(request.getLogin()==null ? user.getLogin():request.getLogin());
            user.setPassword(request.getPassword()==null ? user.getPassword():request.getPassword());
            user.setType(request.getType()==null ? user.getType():request.getType());
            user.setStatus(Status.UPDATED);
            user.setUpdatedAt(LocalDateTime.now());
            user=userRepo.save(user);}
        else{
            log.error("User not found");
        }
        return mapper.convertValue(user,UserInfoResponse.class);
    }
    @Override
    public User getUserDb(Long id){
        return userRepo.findById(id).orElseThrow(()->new CustomException("User is not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserDb(id);
        if (user.getId() != null) {
            user.setStatus(Status.DELETED);
            user.setUpdatedAt(LocalDateTime.now());
            userRepo.save(user);}
        else{
            log.error("User not found");
        }
    }

    public User findByLogin(String login){
        return userRepo.findByLogin(login);

    }

    @Override
    public Integer getUserType(String login) {
        return userRepo.getType(login);
    }

    @Override
    public UserInfoRequest convertUser(User s){
        return mapper.convertValue(s, UserInfoRequest.class);
    }

}

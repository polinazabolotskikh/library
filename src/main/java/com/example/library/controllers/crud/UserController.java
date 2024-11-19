package com.example.library.controllers.crud;

import com.example.library.model.db.entity.User;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.request.UserInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.UserInfoResponse;
import com.example.library.service.BookService;
import com.example.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    @Operation(summary = "Добавление пользователя")
    public UserInfoResponse createUser(@RequestBody @Valid UserInfoRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя")
    public UserInfoResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование пользователя")
    public UserInfoResponse updateUser(@PathVariable Long id, @RequestBody @Valid UserInfoRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{login}")
    @Operation(summary = "Получение пользователя по логину")
    public User findByLogin(@PathVariable String login) {
        return userService.findByLogin(login);
    }

    @GetMapping("/login/{login}")
    @Operation(summary = "Получение типа пользователя")
    public Integer getUserType(@PathVariable String login) {
        return userService.getUserType(login);
    }


}

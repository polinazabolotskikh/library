package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.entity.User;
import com.example.library.model.dto.response.UserInfoResponse;
import com.example.library.service.ReaderService;
import com.example.library.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthorisationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReaderService readerService;
    @GetMapping("/auto")
    public String auto(){
        return "authorisation";
    }
    @PostMapping("/auto")
    public String handleLogin(@RequestParam("login") String login,
                              @RequestParam("password") String password,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = userService.findByLogin(login);
        Integer userType = userService.getUserType(login);
        // Проверяем логин и пароль в базе данных или другом хранилище
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            session.setAttribute("userType", userType);
            // Определяем целевую страницу для перенаправления в зависимости от типа пользователя
            String redirectUrl = determineRedirectUrl(userType, login, session);
            return "redirect:" + redirectUrl;
        } else {
            // Если логин и/или пароль не совпадают, отправляем сообщение об ошибке и перенаправляем обратно на страницу входа
            redirectAttributes.addFlashAttribute("error", "Неправильный логин или пароль");
            return "redirect:/auto";
        }
    }

    private String determineRedirectUrl(Integer u, String login, HttpSession session) {
        // Определяем целевую страницу для перенаправления в зависимости от типа пользователя
        switch (u) {
            case 0:
                session.setAttribute("userType", u);
                return "/admin"; // Перенаправляем администратора на страницу администратора
            case 1:
                    session.setAttribute("userType", u);
                    Reader r = readerService.findByCard(login);
                    session.setAttribute("libraryCard", r.getLibrary_card());
                    session.setAttribute("readerName", r.getFio());
                    session.setAttribute("readerAge", r.getAge());
                    session.setAttribute("readerPhone", r.getPhone());
                    session.setAttribute("readerEmail", r.getEmail());
                    // Перенаправляем на страницу с данными читателя
                    return "/reader";

            case 2:
                session.setAttribute("userType", u);
                return "/employee"; // Перенаправляем обычного пользователя на страницу пользователя
            case 3:
                session.setAttribute("userType", u);
                return "/manager"; // Перенаправляем обычного пользователя на страницу пользователя
            default:
                return "/"; // По умолчанию перенаправляем на главную страницу
        }
}}

package com.example.library.controllers.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstPageController {
    @GetMapping("/")
    public String first(){
        return "first_page";
    }

}

package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Employee;
import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.repository.ReaderRepo;
import com.example.library.service.EmployeeService;
import com.example.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/admin")
    public String mainPage(Model model){
        List<Reader> readers = readerService.getAllReaders();
        model.addAttribute("readers", readers);
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "main_page_admin";
    }



}

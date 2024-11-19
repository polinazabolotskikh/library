package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Employee;
import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.entity.User;
import com.example.library.model.dto.request.EmployeeInfoRequest;
import com.example.library.model.dto.request.ReaderInfoRequest;
import com.example.library.model.dto.request.UserInfoRequest;
import com.example.library.model.enums.Function;
import com.example.library.model.enums.Shift;
import com.example.library.model.enums.UserType;
import com.example.library.service.EmployeeService;
import com.example.library.service.ReaderService;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.mindrot.jbcrypt.BCrypt;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    ReaderService readerService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/register")
    public String main(){
        return "register";
    }

    @PostMapping("/add_user")
    public String addUser(@RequestParam("login") String login,
                          @RequestParam("password") String password,
                          @RequestParam("type") String type,
                          RedirectAttributes redirectAttributes){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setType(UserType.valueOf(type));
        UserInfoRequest userInfoRequest = userService.convertUser(user);
        userService.createUser(userInfoRequest);
        return "redirect:/register";
    }

    @PostMapping("/add_reader")
    public String addReader(@RequestParam("fio") String fio,
                          @RequestParam("age") String age,
                          @RequestParam("phone") String phone,
                          @RequestParam("email") String email,
                          @RequestParam("libraryCard") String libraryCard,
                          RedirectAttributes redirectAttributes){
        try {
            Integer age1= Integer.parseInt(age);
            Reader reader = new Reader();
            reader.setLibrary_card(libraryCard);
            reader.setFio(fio);
            reader.setAge(age1);
            reader.setPhone(phone);
            reader.setEmail(email);
            ReaderInfoRequest readerInfoRequest=readerService.convertReader(reader);
            readerService.createReader(readerInfoRequest);
            return "redirect:/admin";
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Значение возраста должно быть числом");
            return "redirect:/register";
        }


    }

    @PostMapping("/add_employee")
    public String addEmployee(@RequestParam("fio") String fio,
                              @RequestParam("phone") String phone,
                          @RequestParam("function") String function,
                          @RequestParam("shift") String shift,
                          RedirectAttributes redirectAttributes){

            Employee employee = new Employee();
            employee.setFio(fio);
            employee.setPhone(phone);
            employee.setFunction(Function.valueOf(function));
            employee.setShift(Shift.valueOf(shift));
            EmployeeInfoRequest employeeInfoRequest=employeeService.convertEmployee(employee);
            employeeService.createEmployee(employeeInfoRequest);
            return "redirect:/admin";

    }



}

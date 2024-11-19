package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Request;
import com.example.library.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RequestsController {

    @Autowired
    RequestService requestService;

    @GetMapping("/requests")
    public String mainPage(Model model){
        List<Request> requests = requestService.getAllRequests();
        for (int i = 0; i < requests.size(); i++) {
            Request request = requests.get(i);
            String yearAsString = String.valueOf(request.getYear());
            model.addAttribute("year_" + (i), yearAsString);
        }

        model.addAttribute("requests", requests);
        return "requests";
    }

    @PostMapping("/takeRequest")
    public String takeRequest(@RequestParam("requestId") Long id,
                              @RequestParam("nameBook") String book,
                              @RequestParam("quantity") Integer quantity,
                              HttpSession session){
        session.setAttribute("requestId",id);
        session.setAttribute("nameBook",book);
        session.setAttribute("quantity",quantity);
        return "supplier";
    }

}

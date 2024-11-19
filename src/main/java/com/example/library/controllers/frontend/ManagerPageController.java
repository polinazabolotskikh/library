package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Supply;
import com.example.library.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManagerPageController {
    @Autowired
    SupplyService supplyService;
    @GetMapping("/manager")
    public String mainPage(Model model){
        List<Supply> supplies = supplyService.getAllSupplies();
        model.addAttribute("supplies", supplies);
        return "main_page_manager";
    }

}

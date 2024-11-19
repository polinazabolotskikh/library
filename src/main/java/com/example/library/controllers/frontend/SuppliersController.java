package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Request;
import com.example.library.model.db.entity.Supply;
import com.example.library.model.dto.request.SupplyInfoRequest;
import com.example.library.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SuppliersController {

    @Autowired
    SupplyService supplyService;

    @GetMapping("/supplier")
    public String mainPage(Model model){
        List<Supply> supplies = supplyService.getAllSupplies();
        model.addAttribute("supplies", supplies);
        return "supplier";
    }

    @PostMapping("/add_supply")
    public String addSupply(@RequestParam("name") String name,
                            @RequestParam("phone") String phone,
                            HttpSession session){
        Integer quantity = (Integer) session.getAttribute("quantity");
        Long id = (Long) session.getAttribute("requestId");
        Supply supply = new Supply();
        supply.setProvider(name);
        supply.setRequest(id);
        supply.setPhone(phone);
        supply.setQuantity(quantity);
        SupplyInfoRequest s = supplyService.convertSupply(supply);
        supplyService.createSupply(s);
        return "redirect:/requests";
    }
}

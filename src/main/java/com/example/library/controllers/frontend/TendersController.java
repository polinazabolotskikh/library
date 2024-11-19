package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Request;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.request.RequestInfoRequest;
import com.example.library.model.enums.Rate;
import com.example.library.model.enums.Type;
import com.example.library.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TendersController {

    @Autowired
    RequestService requestService;

    @GetMapping("/tenders")
    public String mainPage(){
        return "tenders";
    }

    @PostMapping("/add_tender")
    public String addTender(@RequestParam("title") String title,
                          @RequestParam("author") String author,
                          @RequestParam("year") String year,
                          @RequestParam("quantity") String quantity,
                          RedirectAttributes redirectAttributes){
        try {
            Integer year1= Integer.parseInt(year);
            try {
                    Integer quantity1= Integer.parseInt(quantity);
                    Request request = new Request();
                    request.setNameBook(title);
                    request.setAuthor(author);
                    request.setYear(year1);
                    request.setQuantityNeed(quantity1);
                    RequestInfoRequest request1 = requestService.convertRequest(request);
                    requestService.createRequest(request1);
                    return "redirect:/requests";
                } catch (NumberFormatException e) {
                    redirectAttributes.addFlashAttribute("error", "Значение количества должно быть числом");
                }

        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Значение года должно быть числом");
        }



        return "redirect:/requests";
    }

}

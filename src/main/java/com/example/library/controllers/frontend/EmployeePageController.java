package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Book;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.enums.Rate;
import com.example.library.model.enums.Type;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeePageController {

    @Autowired
    BookService bookService;

    @GetMapping("/employee")
    public String mainPage(){
        return "main_page_employee";
    }

    @PostMapping("/add_book")
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("author") String author,
                          @RequestParam("year") String year,
                          @RequestParam("rating") String rating,
                          @RequestParam("type") String type,
                          @RequestParam("cost") String cost,
                          @RequestParam("quantity") String quantity,
                          RedirectAttributes redirectAttributes){
        try {
            Integer year1= Integer.parseInt(year);
            try {
                Float cost1= Float.parseFloat(cost);
                try {
                    Integer quantity1= Integer.parseInt(quantity);
                    Book book = new Book();
                    book.setNameBook(title);
                    book.setAuthor(author);
                    book.setTypeBook(Type.valueOf(type));
                    book.setYear(year1);
                    book.setQuantity(quantity1);
                    book.setCost(cost1);
                    book.setRating(Rate.valueOf(rating));
                    BookInfoRequest request = bookService.convertBook(book);
                    bookService.createBook(request);
                    return "redirect:/catalog";
                } catch (NumberFormatException e) {
                    redirectAttributes.addFlashAttribute("error", "Значение количества должно быть числом");
                }
            } catch (NumberFormatException e) {
                redirectAttributes.addFlashAttribute("error", "Значение цены должно быть числом");
            }
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("error", "Значение года должно быть числом");
        }



        return "redirect:/catalog";
    }
}

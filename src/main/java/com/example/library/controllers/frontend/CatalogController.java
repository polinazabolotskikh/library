package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.entity.Reservation;
import com.example.library.model.db.repository.BookRepo;
import com.example.library.model.db.repository.ReaderRepo;
import com.example.library.model.db.repository.ReservationRepo;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.request.ReservationInfoRequest;
import com.example.library.service.BookService;
import com.example.library.service.ReaderService;
import com.example.library.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CatalogController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderRepo readerRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ReservationRepo reservationRepo;


    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("errorMessage", "Книга имеет рейтинг 18+, вы не можете взять ее");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            String yearAsString = String.valueOf(book.getYear());
            model.addAttribute("year_" + (i), yearAsString);
        }

        model.addAttribute("books", books);
        return "catalog";
    }

    @PostMapping("/catalog")
    public String takeBook(@ModelAttribute("bookId") Long bookId, @ModelAttribute("libraryCard") String libraryCard, Model model, RedirectAttributes redirectAttributes) {
        Integer age = readerRepo.findAge(libraryCard);
        String rating = bookRepo.getRating(bookId);
        Integer quantity = bookRepo.getQuantity(bookId);
        String type = bookRepo.getType(bookId);
        Long id = reservationRepo.findReservation(libraryCard, bookId);
        Integer number = reservationRepo.countReservation(libraryCard);
        if (quantity > 0 || type == "eBook") {
            if (age < 18 && rating == "R") {
                redirectAttributes.addFlashAttribute("error", "Эта книга имеет рейтинг 18+");
                return "redirect:/catalog";
            } else {
                if (id == null) {
                    if (number < 1 ) {
                        LocalDateTime dateTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        String dateBorrow = dateTime.format(formatter);
                        LocalDateTime futureDateTime = dateTime.plusWeeks(2);
                        String dateReturn = futureDateTime.format(formatter);

                        Reservation reservation = new Reservation();
                        reservation.setBook(bookId);
                        reservation.setLibraryCard(libraryCard);
                        reservation.setDateBorrow(dateBorrow);
                        reservation.setDateReturn(dateReturn);

                        ReservationInfoRequest request = reservationService.convertReservation(reservation);

                        reservationService.createReservation(request);
                        quantity = quantity - 1;
                        Book book = new Book();
                        book.setQuantity(quantity);
                        BookInfoRequest request1 = bookService.convertBook(book);
                        bookService.updateBook(bookId, request1);

                        return "redirect:/reader";
                    } else {
                        redirectAttributes.addFlashAttribute("error", "Вы достигли лимита книг!");
                        return "redirect:/catalog";
                    }
                } else {
                    redirectAttributes.addFlashAttribute("error", "Эту книгу вы уже брали!");
                    return "redirect:/catalog";
                }
            }

        }
        else {
            redirectAttributes.addFlashAttribute("error", "Нет доступных экземпляров");
            return "redirect:/catalog";
        }

}
}


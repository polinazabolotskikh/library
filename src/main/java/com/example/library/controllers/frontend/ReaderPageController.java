package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.entity.Reservation;
import com.example.library.model.db.repository.ReservationRepo;
import com.example.library.model.dto.request.ReservationInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.service.BookService;
import com.example.library.service.ReaderService;
import com.example.library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

@Controller
public class ReaderPageController {
    @Autowired
    BookService bookService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReaderService readerService;

    @Autowired
    ReservationRepo reservationRepo;
    @GetMapping("/reader")
    public String mainPage(HttpSession session, Model model){
        String readerName = (String) session.getAttribute("readerName");
        Integer age = (Integer) session.getAttribute("readerAge");
        String phone = (String) session.getAttribute("readerPhone");
        String email = (String) session.getAttribute("readerEmail");
        String libraryCard = (String) session.getAttribute("libraryCard");
        List<Long> books_id = reservationRepo.findBook(libraryCard);
        List<Book> books = new ArrayList<>();
        for (Long bookId : books_id) {
            BookInfoResponse book = bookService.getBook(bookId);
            Reservation reservation = new Reservation();
            Book book1 = new Book();
            String dateBorrow = reservationRepo.getDateBorrow(libraryCard, bookId);
            String dateReturn = reservationRepo.getReturn(libraryCard,bookId);
            book1.setId(book.getId());
            book1.setNameBook(book.getNameBook());
            book1.setAuthor(book.getAuthor());
            book1.setTypeBook(book.getTypeBook());
            books.add(book1);
            model.addAttribute("dateBorrow",dateBorrow);
            model.addAttribute("dateReturn",dateReturn);
        }

        model.addAttribute("readerName", readerName);
        model.addAttribute("readerAge", age);
        model.addAttribute("readerPhone", phone);
        model.addAttribute("readerEmail", email);
        model.addAttribute("books", books);

        return "main_page_reader";
    }
    @PostMapping("/extend")
    public String extendBook(@ModelAttribute("bookId") Long bookId,Model model, RedirectAttributes redirectAttributes, HttpSession session){
        String libraryCard = (String) session.getAttribute("libraryCard");
        Long id = reservationRepo.findReservation(libraryCard, bookId);
        String dateBorrow=reservationRepo.getDateBorrow(libraryCard,bookId);
        String dateReturn1=reservationRepo.getReturn(libraryCard,bookId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateReturn1, formatter);
        LocalDateTime dateTime = date.atStartOfDay();
        LocalDateTime newDateTime = dateTime.plusWeeks(2);
        LocalDate date1 = LocalDate.parse(dateBorrow, formatter);
        LocalDateTime dateTime1 = date1.atStartOfDay();
        LocalDateTime newDateTime1 = dateTime1.plusWeeks(4);
        if (newDateTime.isAfter(newDateTime1)){
            redirectAttributes.addFlashAttribute("error", "Вы не можете продлить более чем на месяц!");
        }
        else{
        String newDateReturn = newDateTime.format(formatter);
        Reservation reservation = new Reservation();
        reservation.setDateReturn(newDateReturn);
        ReservationInfoRequest request = reservationService.convertReservation(reservation);
        reservationService.updateReservation(id,request);}
        return "redirect:/reader";
    }

}

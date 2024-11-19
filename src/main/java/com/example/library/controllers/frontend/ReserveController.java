package com.example.library.controllers.frontend;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.entity.Reservation;
import com.example.library.model.db.repository.BookRepo;
import com.example.library.model.db.repository.ReservationRepo;
import com.example.library.model.dto.extra.ReservationDTO;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.response.ReservationInfoResponse;
import com.example.library.service.BookService;
import com.example.library.service.ReaderService;
import com.example.library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReserveController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReaderService readerService;
    @Autowired
    BookService bookService;

    @Autowired
    BookRepo bookRepo;


    @GetMapping("/reserve")
    public String mainPage(){
        return "reservation";
    }

    @PostMapping("/find_reader")
    public String findReader(@RequestParam("readerId") String libraryCard, RedirectAttributes redirectAttributes, Model model){
        List<Reservation> reserves = reservationService.getAllReservations(libraryCard);

        Reader reader=readerService.findByCard(libraryCard);

        List<ReservationDTO> reservationDTOs = new ArrayList<>();

        for (Reservation res:reserves){
            Long book = res.getBook();
            Book book1 = bookService.getBook1(book);
            ReservationDTO reservationDTO = new ReservationDTO();

            model.addAttribute("id",res.getId());
            reservationDTO.setBookName(book1.getNameBook());
            reservationDTO.setDateBorrow(res.getDateBorrow());
            reservationDTO.setDateReturn(res.getDateReturn());
            reservationDTOs.add(reservationDTO);

        }

        model.addAttribute("reader",reader.getFio());
        model.addAttribute("reservations", reservationDTOs);

        return "reservation";
    }


    @PostMapping("/cancel_reservation")
    public String cancelReservation(@RequestParam("id") Long reservationId, RedirectAttributes redirectAttributes) {
        ReservationInfoResponse reservation=reservationService.getReservation(reservationId);
        Long bookId=reservation.getBook();
        Integer quantity = bookRepo.getQuantity(bookId);
        quantity = quantity + 1;
        Book book = new Book();
        book.setQuantity(quantity);
        BookInfoRequest request1 = bookService.convertBook(book);
        bookService.updateBook(bookId, request1);
        reservationService.deleteReservation(reservationId);
        return "reservation";
    }
}

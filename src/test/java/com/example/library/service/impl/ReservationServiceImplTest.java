package com.example.library.service.impl;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.entity.Reservation;
import com.example.library.model.db.repository.BookRepo;
import com.example.library.model.db.repository.ReservationRepo;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.request.ReservationInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.ReaderInfoResponse;
import com.example.library.model.dto.response.ReservationInfoResponse;
import com.example.library.model.enums.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {
    @InjectMocks
    private ReservationServiceImpl reservationService;
    @Mock
    private ReservationRepo reservationRepo;
    @Spy
    private ObjectMapper mapper;
    @Test
    public void createReservation() {
        Reservation reservation =new Reservation();
        reservation.setId(1L);
        /*reservation.setLibraryCard(new Reader());
        reservation.setBook(new Book());*/
        when(reservationRepo.save(any(Reservation.class)))
                .thenReturn(reservation);
        ReservationInfoRequest request=new ReservationInfoRequest();
        ReservationInfoResponse result = reservationService.createReservation(request);
        assertEquals(Long.valueOf(1L),result.getId());
    }

    @Test
    public void getReservation() {
    }

    @Test
    public void getReservationDb() {
    }

    @Test
    public void updateReservation() {
        ReservationInfoRequest request=new ReservationInfoRequest();
        request.setDateReturn("11.12.2023");
        Reservation reservation=new Reservation();
        reservation.setId(1L);
        reservation.setDateReturn("12.01.2024");
        reservation.setDateBorrow("15.11.2023");

        when(reservationRepo.findById(reservation.getId())).thenReturn(Optional.of(reservation));
        when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation);

        ReservationInfoResponse result=reservationService.updateReservation(reservation.getId(),request);
        assertEquals(reservation.getDateBorrow(),result.getDateBorrow());
        assertEquals(request.getDateReturn(),result.getDateReturn());
    }

    @Test
    public void deleteReservation() {
        Reservation reservation=new Reservation();
        reservation.setId(1L);
        when(reservationRepo.findById(reservation.getId())).thenReturn(Optional.of(reservation));
        reservationService.deleteReservation(reservation.getId());
        verify(reservationRepo,times(1)).save(any(Reservation.class));
        assertEquals(Status.DELETED,reservation.getStatus());
    }
}
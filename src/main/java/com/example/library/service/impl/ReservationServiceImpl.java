package com.example.library.service.impl;

import com.example.library.exceptions.CustomException;
import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Reservation;
import com.example.library.model.db.repository.ReservationRepo;
import com.example.library.model.dto.request.ReservationInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.ReservationInfoResponse;
import com.example.library.model.enums.Status;
import com.example.library.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepo reservationRepo;
    private final ObjectMapper mapper;
    @Override
    public ReservationInfoResponse createReservation(ReservationInfoRequest request) {
        Reservation reservation = mapper.convertValue(request, Reservation.class);
        reservation.setStatus(Status.CREATED);
        reservation.setCreatedAt(LocalDateTime.now());
        reservation = reservationRepo.save(reservation);
        return mapper.convertValue(reservation, ReservationInfoResponse.class);
    }

    @Override
    public ReservationInfoResponse getReservation(Long id) {
        return mapper.convertValue(getReservationDb(id), ReservationInfoResponse.class);
    }
    @Override
    public Reservation getReservationDb(Long id) {
        return reservationRepo.findById(id).orElseThrow(()->new CustomException("Reservation is not found", HttpStatus.NOT_FOUND));

    }

    @Override
    public ReservationInfoResponse updateReservation(Long id, ReservationInfoRequest request) {
        Reservation reservation = getReservationDb(id);
        if (reservation.getId() != null) {
            reservation.setDateBorrow(request.getDateBorrow()==null ? reservation.getDateBorrow():request.getDateBorrow());
            reservation.setDateReturn(request.getDateReturn()==null ? reservation.getDateReturn():request.getDateReturn());
            reservation.setStatus(Status.UPDATED);
            reservation.setUpdatedAt(LocalDateTime.now());
            reservation=reservationRepo.save(reservation);
        }
        else{
            log.error("Reservation not found");
        }
        return mapper.convertValue(reservation, ReservationInfoResponse.class);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = getReservationDb(id);
        if (reservation.getId() != null) {
            reservation.setStatus(Status.DELETED);
            reservation.setUpdatedAt(LocalDateTime.now());
            reservationRepo.save(reservation);}
        else{
            log.error("Reservation not found");
        }
    }
}

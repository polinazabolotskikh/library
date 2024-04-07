package com.example.library.service;

import com.example.library.model.db.entity.Reservation;
import com.example.library.model.dto.request.ReservationInfoRequest;
import com.example.library.model.dto.response.ReservationInfoResponse;

public interface ReservationService {
    ReservationInfoResponse createReservation(ReservationInfoRequest request);

    ReservationInfoResponse getReservation(Long id);

    Reservation getReservationDb(Long id);

    ReservationInfoResponse updateReservation(Long id, ReservationInfoRequest request);

    void deleteReservation(Long id);
}

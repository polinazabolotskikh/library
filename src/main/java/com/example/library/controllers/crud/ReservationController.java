package com.example.library.controllers.crud;

import com.example.library.model.dto.request.RequestInfoRequest;
import com.example.library.model.dto.request.ReservationInfoRequest;
import com.example.library.model.dto.response.RequestInfoResponse;
import com.example.library.model.dto.response.ReservationInfoResponse;
import com.example.library.service.RequestService;
import com.example.library.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    @PostMapping
    @Operation(summary = "Резервирование книги")
    public ReservationInfoResponse createReservation(@RequestBody @Valid ReservationInfoRequest request) {
        return reservationService.createReservation(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации о резервировании")
    public ReservationInfoResponse getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование информации о резервировании")
    public ReservationInfoResponse updateReservation(@PathVariable Long id, @RequestBody @Valid ReservationInfoRequest request) {
        return reservationService.updateReservation(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление информации о резервировании")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}

package com.example.library.controllers;

import com.example.library.model.dto.request.EmployeeInfoRequest;
import com.example.library.model.dto.request.ProviderInfoRequest;
import com.example.library.model.dto.request.RequestInfoRequest;
import com.example.library.model.dto.response.ProviderInfoResponse;
import com.example.library.model.dto.response.RequestInfoResponse;
import com.example.library.service.ProviderService;
import com.example.library.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    @PostMapping
    @Operation(summary = "Добавление заявки")
    public RequestInfoResponse createRequest(@RequestBody @Valid RequestInfoRequest request) {
        return requestService.createRequest(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение заявки")
    public RequestInfoResponse getRequest(@PathVariable Long id) {
        return requestService.getRequest(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование заявки")
    public RequestInfoResponse updateRequest (@PathVariable Long id, @RequestBody @Valid RequestInfoRequest request) {
        return requestService.updateRequest(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление заявки")
    public void deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
    }
}

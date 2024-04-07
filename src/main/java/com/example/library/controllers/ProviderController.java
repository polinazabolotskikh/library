package com.example.library.controllers;

import com.example.library.model.dto.request.EmployeeInfoRequest;
import com.example.library.model.dto.request.ProviderInfoRequest;
import com.example.library.model.dto.response.EmployeeInfoResponse;
import com.example.library.model.dto.response.ProviderInfoResponse;
import com.example.library.service.EmployeeService;
import com.example.library.service.ProviderService;
import com.example.library.service.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
    @PostMapping
    @Operation(summary = "Добавление поставщика")
    public ProviderInfoResponse createProvider(@RequestBody @Valid ProviderInfoRequest request) {
        return providerService.createProvider(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение поставщика")
    public ProviderInfoResponse getProvider(@PathVariable Long id) {
        return providerService.getProvider(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование поставщика")
    public ProviderInfoResponse updateProvider (@PathVariable Long id, @RequestBody @Valid ProviderInfoRequest request) {
        return providerService.updateProvider(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление поставщика")
    public void deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
    }
}

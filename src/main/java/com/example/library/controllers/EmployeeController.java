package com.example.library.controllers;

import com.example.library.model.dto.request.EmployeeInfoRequest;
import com.example.library.model.dto.response.EmployeeInfoResponse;
import com.example.library.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping
    @Operation(summary = "Добавление работника")
    public EmployeeInfoResponse createEmployee(@RequestBody @Valid EmployeeInfoRequest request) {
        return employeeService.createEmployee(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение работника")
    public EmployeeInfoResponse getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование работника")
    public EmployeeInfoResponse updateEmployee (@PathVariable Long id, @RequestBody @Valid EmployeeInfoRequest request) {
        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление работника")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}

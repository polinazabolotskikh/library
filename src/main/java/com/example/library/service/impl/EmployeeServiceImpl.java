package com.example.library.service.impl;

import com.example.library.exceptions.CustomException;
import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Employee;
import com.example.library.model.db.repository.EmployeeRepo;
import com.example.library.model.dto.request.EmployeeInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.EmployeeInfoResponse;
import com.example.library.model.enums.Status;
import com.example.library.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final ObjectMapper mapper;
    @Override
    public EmployeeInfoResponse createEmployee(EmployeeInfoRequest request) {
        Employee employee = mapper.convertValue(request, Employee.class);
        employee.setStatus(Status.CREATED);
        employee.setCreatedAt(LocalDateTime.now());
        employee = employeeRepo.save(employee);
        return mapper.convertValue(employee, EmployeeInfoResponse.class);
    }

    @Override
    public EmployeeInfoResponse getEmployee(Long id) {
        return mapper.convertValue(getEmployeeDb(id), EmployeeInfoResponse.class);
    }

    @Override
    public Employee getEmployeeDb(Long id) {
        return employeeRepo.findById(id).orElseThrow(()->new CustomException("Employee is not found", HttpStatus.NOT_FOUND));

    }

    @Override
    public EmployeeInfoResponse updateEmployee(Long id, EmployeeInfoRequest request) {
        Employee employee=getEmployeeDb(id);
        if (employee.getId() != null) {
        employee.setFio(request.getFio()==null ? employee.getFio():request.getFio());
        employee.setPhone(request.getPhone()==null ? employee.getPhone():request.getPhone());
        employee.setFunction(request.getFunction()==null ? employee.getFunction():request.getFunction());
        employee.setShift(request.getShift()==null ? employee.getShift():request.getShift());
        employee.setStatus(Status.UPDATED);
        employee.setUpdatedAt(LocalDateTime.now());
        employee=employeeRepo.save(employee);}
        else{
            log.error("Employee not found");
        }
        return mapper.convertValue(employee,EmployeeInfoResponse.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeDb(id);
        if (employee.getId() != null) {
        employee.setStatus(Status.DELETED);
        employee.setUpdatedAt(LocalDateTime.now());
        employeeRepo.save(employee);}
        else{
        log.error("Employee not found");
    }
    }
}

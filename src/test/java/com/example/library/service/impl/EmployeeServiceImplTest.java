package com.example.library.service.impl;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Employee;
import com.example.library.model.db.repository.BookRepo;
import com.example.library.model.db.repository.EmployeeRepo;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.request.EmployeeInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.EmployeeInfoResponse;
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
public class EmployeeServiceImplTest {
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Mock
    private EmployeeRepo employeeRepo;
    @Spy
    private ObjectMapper mapper;
    @Test
    public void createEmployee() {
        Employee employee =new Employee();
        employee.setId(1L);
        when(employeeRepo.save(any(Employee.class)))
                .thenReturn(employee);
        EmployeeInfoRequest request=new EmployeeInfoRequest();
        EmployeeInfoResponse result = employeeService.createEmployee(request);
        assertEquals(Long.valueOf(1L),result.getId());
    }

    @Test
    public void getEmployee() {
    }

    @Test
    public void getEmployeeDb() {
    }

    @Test
    public void updateEmployee() {
        EmployeeInfoRequest request=new EmployeeInfoRequest();
        request.setPhone("89370772717");
        Employee employee=new Employee();
        employee.setId(1L);
        employee.setPhone("89377589036");
        employee.setFio("Ivanov Ivan Ivanovich");

        when(employeeRepo.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(employeeRepo.save(any(Employee.class))).thenReturn(employee);

        EmployeeInfoResponse result=employeeService.updateEmployee(employee.getId(),request);
        assertEquals(employee.getFio(),result.getFio());
        assertEquals(request.getPhone(),result.getPhone());
    }

    @Test
    public void deleteEmployee() {
        Employee employee=new Employee();
        employee.setId(1L);
        when(employeeRepo.findById(employee.getId())).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(employee.getId());
        verify(employeeRepo,times(1)).save(any(Employee.class));
        assertEquals(Status.DELETED,employee.getStatus());
    }
}
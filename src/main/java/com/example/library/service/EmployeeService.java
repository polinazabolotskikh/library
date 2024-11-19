package com.example.library.service;

import com.example.library.model.db.entity.Employee;
import com.example.library.model.dto.request.EmployeeInfoRequest;
import com.example.library.model.dto.response.EmployeeInfoResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeInfoResponse createEmployee(EmployeeInfoRequest request);

    EmployeeInfoResponse getEmployee(Long id);

    Employee getEmployeeDb(Long id);

    EmployeeInfoResponse updateEmployee(Long id, EmployeeInfoRequest request);

    void deleteEmployee(Long id);

    List<Employee> getAllEmployees();

    EmployeeInfoRequest convertEmployee(Employee s);
}

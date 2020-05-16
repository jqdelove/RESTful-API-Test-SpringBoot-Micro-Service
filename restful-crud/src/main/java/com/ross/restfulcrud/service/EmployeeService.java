package com.ross.restfulcrud.service;

import com.ross.restfulcrud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee login(String name, String password);

    List<Employee> getAll();

    int addEmp(Employee employee);
}

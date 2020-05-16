package com.ross.restfulcrud.service;

import com.ross.restfulcrud.entity.Employee;

public interface EmployeeService {

    Employee login(String name, String password);
}

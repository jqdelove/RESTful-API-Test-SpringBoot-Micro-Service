package com.ross.restfulcrud.service;

import com.ross.restfulcrud.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department getDept(Integer id);

    List<Department> getAll();
}

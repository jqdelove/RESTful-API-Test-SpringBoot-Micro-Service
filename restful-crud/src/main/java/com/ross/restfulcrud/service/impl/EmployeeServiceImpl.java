package com.ross.restfulcrud.service.impl;

import com.ross.restfulcrud.entity.Employee;
import com.ross.restfulcrud.mapper.EmployeeMapper;
import com.ross.restfulcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(String name, String password) {
        Employee employee = employeeMapper.selectByNameAndPwd(name, password);
        if (null!=employee){
            return employee;
        }else {
            return null;
        }
    }
}

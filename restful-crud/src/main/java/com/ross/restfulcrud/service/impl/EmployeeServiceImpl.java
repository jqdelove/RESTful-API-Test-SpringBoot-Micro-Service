package com.ross.restfulcrud.service.impl;

import com.ross.restfulcrud.entity.Employee;
import com.ross.restfulcrud.mapper.EmployeeMapper;
import com.ross.restfulcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    //常用的查询进行缓存
    @Cacheable(cacheNames = "emps")
    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int addEmp(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }

    @Cacheable(cacheNames = "emp",key = "#id")
    @Override
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    //更新缓存，key要和上面查询的key相同
    @CachePut(cacheNames = "emp",key = "#result.id")
    @Override
    public Employee modify(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
        return employee;
    }

    @CacheEvict(cacheNames = "emp",key = "#id")
    @Override
    public int remove(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }
}

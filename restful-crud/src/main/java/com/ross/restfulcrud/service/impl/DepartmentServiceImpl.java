package com.ross.restfulcrud.service.impl;

import com.ross.restfulcrud.entity.Department;
import com.ross.restfulcrud.mapper.DepartmentMapper;
import com.ross.restfulcrud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("departmentService")
@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Department getDept(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    //常用的查询结果进行缓存
    @Cacheable(cacheNames = "depts")
    @Override
    public List<Department> getAll() {
        return departmentMapper.selectAll();
    }
}

package com.ross.restfulcrud.controller;

import com.ross.restfulcrud.entity.Employee;
import com.ross.restfulcrud.entity.User;
import com.ross.restfulcrud.exception.UserNotExistException;
import com.ross.restfulcrud.service.DepartmentService;
import com.ross.restfulcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TrailController {
    
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/trail")
    public String trail(Map<String, String> map){
        User user = new User();
        user.setUserName("金奇");
        map.put("trail","测试");

        return "trail";
    }

    //加ResponseBody注解表示直接返回字符串，而不是由模板引擎解析页面
    @ResponseBody
    @GetMapping("/exception")
    public String exception(@RequestParam("user") String user){
        if (user.equals("111")){
            throw new UserNotExistException();
        }
        return "success";
    }

    //返回json对象
    @GetMapping(value = "/json",produces = "application/json;charset=UTF-8")
    public @ResponseBody Employee  json(){
        Employee emp = employeeService.getEmp(1);
        return emp;
    }
}

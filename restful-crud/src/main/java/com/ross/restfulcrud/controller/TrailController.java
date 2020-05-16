package com.ross.restfulcrud.controller;

import com.ross.restfulcrud.entity.User;
import com.ross.restfulcrud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class TrailController {
    
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/trail")
    public String trail(Map<String, String> map){
        User user = new User();
        user.setUserName("金奇");
        map.put("trail","测试");

        return "trail";
    }
}

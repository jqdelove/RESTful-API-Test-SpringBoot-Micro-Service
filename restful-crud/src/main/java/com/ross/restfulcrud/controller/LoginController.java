package com.ross.restfulcrud.controller;

import com.ross.restfulcrud.entity.Employee;
import com.ross.restfulcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

//    @RequestMapping(value = "/employee/login",method = RequestMethod.POST)
    //上面的写法用PostMapping代替
    @PostMapping(value = "/employee/login")
    public String login(String username, String password, Map<String, String> map, HttpSession session){
        Employee employee = employeeService.login(username, password);
        if (employee!=null) {
            //防止表单重复提交，使用登陆成功后重定向到主页
            session.setAttribute("employee",employee);
            return "redirect:/dashboard.html";
        }else {
            map.put("msg","用户名或密码错误");
            return "login";
        }
    }
}

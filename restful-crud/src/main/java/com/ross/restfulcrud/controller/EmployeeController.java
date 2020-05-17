package com.ross.restfulcrud.controller;

import com.ross.restfulcrud.entity.Department;
import com.ross.restfulcrud.entity.Employee;
import com.ross.restfulcrud.service.DepartmentService;
import com.ross.restfulcrud.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    //处理GET请求
    @GetMapping("/emps")
    public String list(Map<String, List<Employee>> map){
        List<Employee> employeeList = employeeService.getAll();
        map.put("emps",employeeList);

        //返回的字符串，thymeleaf会去到classpath:/templates/为前缀，.html为后缀的页面
        return "emp/list";
    }

    //跳转添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查出所有部门在页面展示
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    //员工添加
    @PostMapping("/emp")
    public String addEmp(@ModelAttribute Employee employee, HttpServletRequest request) throws ParseException {
        String birth = request.getParameter("birth");
//        logger.info(birth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        employee.setBirth(sdf.parse(birth));
        employeeService.addEmp(employee);

        //添加完成，重新查询回到列表页面
        return "redirect:/emps";
    }

    //跳转员工信息修改页面，先查询员工信息
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){//PathVariable注解中填写的表示请求的参数名
        Employee emp = employeeService.getEmp(id);
        model.addAttribute("emp",emp);

        //查出所有部门在页面展示
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments",departments);

        //修改添加页面用同一个页面
        return "emp/add";
    }

    //员工信息修改
    @PutMapping("/emp")
    public String modify(@ModelAttribute Employee employee){
        employeeService.modify(employee);
        return "redirect:/emps";
    }

    //删除员工
    @DeleteMapping("/emp/{id}")
    public String remove(@PathVariable("id") Integer id){
        employeeService.remove(id);
        return "redirect:/emps";
    }
}

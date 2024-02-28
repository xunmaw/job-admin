package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.Enterprise;
import com.xunmaw.graduate.entity.Manager;
import com.xunmaw.graduate.entity.Student;
import com.xunmaw.graduate.service.EnterpriseService;
import com.xunmaw.graduate.service.ManagerService;
import com.xunmaw.graduate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/login")
    public String login(@RequestParam("no") String id, @RequestParam("pwd") String pass, String flag, Model model, HttpServletRequest request){
        //0 管理员 1 企业 2 学生
        HttpSession session = request.getSession();
        if (flag.equals("0")){
            System.out.println("管理员登录");
            Manager manager = managerService.login(id, pass);
            if (manager!=null){
                System.out.println(manager);
                session.setAttribute("manager",manager);
                model.addAttribute("manager",manager);
                return "0";
            }
        }
        if (flag.equals("1")){
            System.out.println("企业登录");
            Enterprise enterprise = enterpriseService.login(id, pass);
            if (enterprise!=null)
            {
                System.out.println(enterprise);
                session.setAttribute("enterprise",enterprise);
                model.addAttribute("enterprise",enterprise);
                return "1";
            }
        }
        if (flag.equals("2")){
            System.out.println("学生登录");
            Student student = studentService.login(id, pass);
            if (student!=null)
            {
                System.out.println(student);
                session = request.getSession();
                session.setAttribute("student",student);
                model.addAttribute("student",student);
                return "2";
            }
        }
        return "false";
    }

    @RequestMapping("/back")
    public String back(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "true";
    }
}

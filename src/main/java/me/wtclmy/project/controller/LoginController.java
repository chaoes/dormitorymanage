package me.wtclmy.project.controller;

import me.wtclmy.project.service.LoginServiceImpl;
import me.wtclmy.project.service.ManagerService;
import me.wtclmy.project.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/

@Controller
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private ManagerService managerService;



    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model,HttpSession session){
        System.out.println("-------error:"+error);
        if(null != session.getAttribute("loginType")&&session.getAttribute("loginType").equals("m")){
            return "redirect:/manager/index";
        }
        if("1".equals(error)){
            model.addAttribute("error",1);
        }
        return "login";
    }

    @PostMapping("/tologin")
    public String tologin(int id, String password, int usertype, HttpSession session){
        System.out.println("---------------id:"+id);
        System.out.println("----------------p:"+password);
        System.out.println("--------------t:"+usertype);
     if(loginService.checkLogin(usertype,id,password)){
         String name = null;
         if(usertype == 0){
             name = studentService.getStudentNamebyId(id);
         }
         else if(usertype == 1){
             name = managerService.getManagerNameById(id);
         }
         if(usertype==0){
             session.setAttribute("loginType","s");
             session.setAttribute("userName",name);
             session.setAttribute("userId",id);
             return "redirect:/student/index";
         }else if(usertype==1){
             session.setAttribute("loginType","m");
             session.setAttribute("userName",name);
             session.setAttribute("userId",id);
             return "redirect:/manager/index";
         }

     }
        return "redirect:/login?error=1";

    }
    @GetMapping("/tologin")
    public String gettologin(){
        return "redirect:/login?error=1";
    }
}

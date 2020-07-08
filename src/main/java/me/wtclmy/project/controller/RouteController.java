package me.wtclmy.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/


@Controller
public class RouteController {
    @RequestMapping("/")
    public String root(HttpSession session){
        if("s"==session.getAttribute("loginType")){
            return "redirect:/student/index";
        }else if("m"==session.getAttribute("loginType")){
            return "redirect:/manager/index";
        }
        else return "redirect:/login";
    }
}

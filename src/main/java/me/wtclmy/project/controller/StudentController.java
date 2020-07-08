package me.wtclmy.project.controller;

import me.wtclmy.project.pojo.Dormitory;
import me.wtclmy.project.pojo.Manager;
import me.wtclmy.project.pojo.Order;
import me.wtclmy.project.pojo.Student;
import me.wtclmy.project.service.DormitoryServiceImpl;
import me.wtclmy.project.service.ManagerServiceImpl;
import me.wtclmy.project.service.OrderServiceImpl;
import me.wtclmy.project.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private DormitoryServiceImpl dormitoryService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ManagerServiceImpl managerService;

    @RequestMapping("/")
    public String router(){
        return "redirect:/student/index";
    }

    @RequestMapping("/index")
    public String index(Model model, HttpSession httpSession){
        model.addAttribute("title","主页");
        model.addAttribute("location","Home");
        model.addAttribute("studentCount",studentService.getStudentCount());
        model.addAttribute("dormitoryCount",dormitoryService.getDormitoryCount());
        model.addAttribute("orderCount",orderService.getOrderCount());
        model.addAttribute("managerCount",managerService.getManagerCount());
        return "index";
    }
    @RequestMapping("/mydormitory")
    public String dormitoryinfo( Model model,HttpSession session){
        int id = (int) session.getAttribute("userId");
        int dormitoryid = studentService.queryStudentById(id).getDormitory().getDormitoryId();
        List<Student> students = studentService.queryStudentByDormitoryId(dormitoryid);
        model.addAttribute("dormitory",dormitoryid);
        model.addAttribute("students",students);
        model.addAttribute("title","宿舍学生信息");
        model.addAttribute("location","DormitoryInfo");
        return "dormitoryinfo";
    }
    @RequestMapping("/myinfo")
    public String myinfo(Model model,HttpSession session){
        int id = (int) session.getAttribute("userId");
        Student user = studentService.queryStudentById(id);
        model.addAttribute("title","个人信息");
        model.addAttribute("location","MyInfo");
        model.addAttribute("user",user);
        return "s_myinfo";
    }
    @RequestMapping("/addorder")
    public String addorder(Model model,HttpSession session){
        int id = (int) session.getAttribute("userId");
        int dormitory = studentService.queryStudentById(id).getDormitory().getDormitoryId();
        model.addAttribute("dormitory",dormitory);
        model.addAttribute("title","提交报修");
        model.addAttribute("location","OrderAdd");
        return "orderadd";

    }
    @RequestMapping("/myorderlist")
    public String orderlist(@RequestParam(required = false,value = "success")String success, @RequestParam(required = false,value = "error")String error, Model model,HttpSession session){
        String msg = null;
        if(success!=null){
            if (success.equals("add")) msg = "提交成功";

        }else if(error!=null){
            if (error.equals("add")) msg="提交失败";
        }
        int id = (int) session.getAttribute("userId");
        int dormitoryid = studentService.queryStudentById(id).getDormitory().getDormitoryId();
        List<Order> orders = orderService.queryOrderByDormitoryId(dormitoryid);
        List<Order> unfinishOrders = new ArrayList<Order>();
        List<Order> finishedOrders = new ArrayList<Order>();
        for(Order order:orders){
            if(order.getOrderIsFinish()==0){
                unfinishOrders.add(order);
            }
            else if(order.getOrderIsFinish()==1){
                finishedOrders.add(order);
            }
        }
        model.addAttribute("title","查看报修");
        model.addAttribute("location","OrderList");
        model.addAttribute("unfinishOrders",unfinishOrders);
        model.addAttribute("finishedOrders",finishedOrders);
        model.addAttribute("msg",msg);
        return "myorderlist";
    }
    @RequestMapping("/toaddorder")
    public String toaddorder(String dormitoryid,String info){
        Order order = new Order();
        order.setOrderInfo(info);
        Dormitory dormitory = new Dormitory(Integer.parseInt(dormitoryid));
        order.setDormitory(dormitory);
        if(1==orderService.addOrder(order)){
            return "redirect:/student/myorderlist?success=add";
        }
        return "redirect:/student/myorderlist?error=add";
    }
    @GetMapping("/infoedit")
    public String infoedit(@RequestParam(required = false,value = "success")String success, @RequestParam(required = false,value = "error")String error,Model model,HttpSession session){
        String msg = null;
        if(success!=null){
            if (success.equals("edit")) msg = "提交成功";

        }else if(error!=null){
            if (error.equals("edit")) msg="提交失败";
            else if(error.equals("password")) msg="密码错误";
        }
        int id = (int) session.getAttribute("userId");
        Student student = studentService.queryStudentById(id);
        HashMap<String,String> user = new HashMap<String, String>();
        user.put("id",student.getStudentId().toString());
        user.put("name",student.getStudentName());
        user.put("phone",student.getStudentPhone());
        model.addAttribute("user",user);
        model.addAttribute("title","查看报修");
        model.addAttribute("location","OrderList");
        model.addAttribute("msg",msg);
        return "infoedit";
    }
    @PostMapping("/toeditinfo")
    public String  toeditinfo(String id,String name,String password1,String password2,String phone,HttpSession session){
        Student student = studentService.queryStudentById(Integer.parseInt(id));
        if(null!=student) {
            if (!student.getStudentPassword().equals(password1)) {
                return "redirect:/student/infoedit?error=password";
            } else {
                student.setStudentPassword(password2);
                student.setStudentName(name);
                student.setStudentPhone(phone);
                if (1 == studentService.updateStudent(student)) {
                    session.setAttribute("userName", student.getStudentName());
                    session.setAttribute("userId", student.getStudentId());
                    return "redirect:/student/infoedit?success=edit";
                }
            }
        }
        return "redirect:/student/infoedit?error=edit";
    }
}

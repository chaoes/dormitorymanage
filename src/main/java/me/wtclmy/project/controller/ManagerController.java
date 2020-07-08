package me.wtclmy.project.controller;

import me.wtclmy.project.pojo.Dormitory;
import me.wtclmy.project.pojo.Manager;
import me.wtclmy.project.pojo.Order;
import me.wtclmy.project.pojo.Student;
import me.wtclmy.project.service.*;
import me.wtclmy.project.utils.MyDate;
import org.apache.ibatis.annotations.Insert;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private DormitoryServiceImpl dormitoryService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private ManagerServiceImpl managerService;
    @Autowired
    private ExcelServiceImpl excelService;
    @RequestMapping("/")
    public String router(){
        return "redirect:/manager/index";
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
    @RequestMapping("/studentlist")
    public String studentlist(@RequestParam(required = false,value = "success")String success,@RequestParam(required = false,value = "error")String error, Model model){
        String msg = null;
        if(success!=null){
            if (success.equals("del")) msg = "删除成功";
            else if (success.equals("add")) msg = "添加成功";
            else if(success.equals("edit")) msg = "修改成功";

        }else if(error!=null){
            if (error.equals("del")) msg="删除失败";
            else if(error.equals("add")) msg="添加失败";
            else if(error.equals("edit")) msg = "修改失败";
        }
        model.addAttribute("msg",msg);
        model.addAttribute("title","学生信息");
        model.addAttribute("location","Studentlist");
        model.addAttribute("students",studentService.queryAllStudent());



        return "studentlist";
    }
    @RequestMapping("/studentedit")
    public String studentedit(String id,Model model,HttpSession session){

        if(null==id){
            return "redirect:/manager/studentlist";
        }
        Student student = studentService.queryStudentById(Integer.parseInt(id));
        List<Dormitory> dormitories = dormitoryService.queryAllDormitory();
        model.addAttribute("dormitories",dormitories);
        model.addAttribute("student",student);
        model.addAttribute("title","信息修改-"+student.getStudentName());
        model.addAttribute("location","StudentEdit");
        return "studentedit";
    }
    @RequestMapping("/tostudentedit")
    public String tostudentedit(String id,String name,String password,String dormitoryid,String phone){
        Student student = new Student(Integer.parseInt(id),name,password,phone,new Dormitory(Integer.parseInt(dormitoryid)));
        if (studentService.updateStudent(student)==1){
            return "redirect:/manager/studentlist?success=edit";
        }
        return "redirect:/manager/studentlist?error=edit";
    }
    @RequestMapping("/studentdel")
    public String studentdel(String id){
        if(studentService.deleteStudent(Integer.parseInt(id))==1){
            return "redirect:/manager/studentlist?success=del";
        }
        return "redirect:/manager/studentlist?error=del";
    }
    @RequestMapping("/studentadd")
    public String studentedit(Model model,HttpSession session){

        List<Dormitory> dormitories = dormitoryService.queryAllDormitory();
        model.addAttribute("dormitories",dormitories);
        model.addAttribute("title","信息修改");
        model.addAttribute("location","StudentAdd");

        return "studentadd";
    }
    @RequestMapping("/tostudentadd")
    public String tostudentadd(String name,String password,String dormitoryid,String phone){
        Student student = new Student(null,name,password,phone,new Dormitory(Integer.parseInt(dormitoryid)));
        if (studentService.addStudent(student)==1){
            return "redirect:/manager/studentlist?success=add";
        }
        return "redirect:/manager/studentlist?error=add";
    }
    @RequestMapping("/orderlist")
    public String orderlist(@RequestParam(required = false,value = "success")String success,@RequestParam(required = false,value = "error")String error,Model model){
        String msg = null;
        if(success!=null){
            if (success.equals("finish")) msg = "确认成功";

        }else if(error!=null){
            if (error.equals("finsish")) msg="确认失败";
        }
        List<Order> unfinishOrders = orderService.queryUnfinishOrder();
        List<Order> finishedOrders = orderService.queryFinishedOrder();
        model.addAttribute("title","查看报修");
        model.addAttribute("location","OrderList");
        model.addAttribute("unfinishOrders",unfinishOrders);
        model.addAttribute("finishedOrders",finishedOrders);
        return "orderlist";
    }
    @RequestMapping("/orderfinish")
    public String orderfinish(String id){
        Order order = orderService.queryOrderById(Integer.parseInt(id));
        order.finishThisOrder();
        if(1==orderService.updateOrder(order)){
            return "redirect:/manager/orderlist?success=finish";
        }
        return "redirect:/manager/orderlist?error=finish";
    }
    @RequestMapping("/myinfo")
    public String myinfo(Model model,HttpSession session){
        int id = (int) session.getAttribute("userId");
        Manager user = managerService.queryManagerById(id);
        model.addAttribute("title","个人信息");
        model.addAttribute("location","MyInfo");
        model.addAttribute("user",user);
        return "m_myinfo";
    }
    @RequestMapping("/dormitorylist")
    public String dormitorylist(@RequestParam(required = false,value = "success")String success,@RequestParam(required = false,value = "error")String error, Model model){
        List<Dormitory> dormitories = dormitoryService.queryAllDormitory();
        String msg = null;
        if(success!=null){
            if (success.equals("del")) msg = "删除成功";
            else if (success.equals("add")) msg = "添加成功";

        }else if(error!=null){
            if (error.equals("del")) msg="删除失败，请检查该宿舍是否有学生或报修单";
            else if(error.equals("add")) msg="添加失败，请确认学生是否已存在";
        }
        model.addAttribute("msg",msg);
        model.addAttribute("title","宿舍信息");
        model.addAttribute("location","DormitoryList");
        model.addAttribute("dormitories",dormitories);
        return "/dormitorylist";
    }
    @RequestMapping("/dormitoryinfo/{id}")
    public String dormitoryinfo(@PathVariable("id")String id,Model model){
        List<Student> students = studentService.queryStudentByDormitoryId(Integer.parseInt(id));
        model.addAttribute("dormitory",id);
        model.addAttribute("students",students);
        model.addAttribute("title",id+"宿舍学生信息");
        model.addAttribute("location","DormitoryInfo");
        return "dormitoryinfo";
    }
    @RequestMapping("/dormitoryadd")
    public String dormitoryadd(Model model){
        model.addAttribute("title","添加宿舍信息");
        model.addAttribute("location","DormitoryAdd");
        return "dormitoryadd";
    }
    @RequestMapping("/todormitoryadd")
    public String todormitoryadd(String id){
        if(1==dormitoryService.addDormitory(new Dormitory(Integer.parseInt(id)))){
            return "redirect:/manager/dormitorylist?success=add";
        }
        return "redirect:/manager/dormitorylist?error=add";
    }
    @RequestMapping("/dormitorydel/{id}")
    public String dormitorydel(@PathVariable("id")String id){
        if(studentService.queryStudentByDormitoryId(Integer.parseInt(id)).size()!=0||orderService.queryOrderByDormitoryId(Integer.parseInt(id)).size()!=0){
            return "redirect:/manager/dormitorylist?error=del";
        }
        else if(1==dormitoryService.deleteDormitory(Integer.parseInt(id))) {
                return "redirect:/manager/dormitorylist?success=del";
            }

        return "redirect:/manager/dormitorylist?error=del";
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
        Manager manager = managerService.queryManagerById(id);
        HashMap<String,String> user = new HashMap<String, String>();
        user.put("id",manager.getManagerId().toString());
        user.put("name",manager.getManagerName());
        user.put("phone",manager.getManagerPhone());
        model.addAttribute("user",user);
        model.addAttribute("title","查看报修");
        model.addAttribute("location","OrderList");
        model.addAttribute("msg",msg);
        return "infoedit";
    }
    @PostMapping("/toeditinfo")
    public String  toeditinfo(String id,String name,String password1,String password2,String phone,HttpSession session){
        Manager manager = managerService.queryManagerById(Integer.parseInt(id));
        if(null!=manager) {
            if (!manager.getManagerPassword().equals(password1)) {
                return "redirect:/manager/infoedit?error=password";
            } else {
                manager.setManagerPassword(password2);
                manager.setManagerName(name);
                manager.setManagerPhone(phone);
                if (1 == managerService.updateManager(manager)) {
                    session.setAttribute("userName", manager.getManagerName());
                    session.setAttribute("userId", manager.getManagerId());
                    return "redirect:/manager/infoedit?success=edit";
                }
            }
        }
        return "redirect:/manager/infoedit?error=edit";
    }
    @RequestMapping("/orderdownload")
    public String orderDownload(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = excelService.getOrderExcel();
        OutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setContentType("application/msexcel");
        response.setHeader("Content-disposition", "attachment; filename="+ URLEncoder.encode(MyDate.dateToString(new Date())+"orderlist.xls", "UTF-8"));
        workbook.write(outputStream);
        outputStream.close();
        return null;
    }


}

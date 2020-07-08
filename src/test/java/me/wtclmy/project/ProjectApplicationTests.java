package me.wtclmy.project;

import me.wtclmy.project.mapper.DormitoryMapper;
import me.wtclmy.project.mapper.ManagerMapper;
import me.wtclmy.project.mapper.OrderMapper;
import me.wtclmy.project.mapper.StudentMapper;
import me.wtclmy.project.pojo.Dormitory;
import me.wtclmy.project.pojo.Manager;
import me.wtclmy.project.pojo.Order;
import me.wtclmy.project.pojo.Student;
import me.wtclmy.project.service.DormitoryService;
import me.wtclmy.project.service.DormitoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ProjectApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private DormitoryServiceImpl dormitoryService;
    @Test
    void contextLoads() {
        Student student = studentMapper.queryStudentById(10001);
        System.out.println(student.toString());
        System.out.println(student.getDormitory().toString());


    }
    @Test
    void testStu(){
        List<Student> st = studentMapper.queryStudentByDormitoryId(1001);
        System.out.println(st.toString());
    }
    @Test
    void testOrd(){
        List<Order> orderl = orderMapper.queryOrderByDormitoryId(1001);
        Order order = orderl.get(0);
        order.setOrderStart(new Date());
        orderMapper.addOrder(order);

    }
    @Test
    public void dortese(){
        Dormitory dormitory = dormitoryMapper.queryDormitoryById(1001);
        System.out.println(dormitory.toString());
    }
    @Test
    public void ds(){
        Dormitory dormitory = new Dormitory(1100);
        dormitoryService.addDormitory(dormitory);
    }

}

package me.wtclmy.project.service;

import me.wtclmy.project.pojo.Student;


import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/


public interface StudentService {

    public String getStudentNamebyId(int id);

    public List<Student> queryAllStudent();

    public Student queryStudentById(int id);

    public List<Student> queryStudentByDormitoryId(int dormitoryId);

    public int deleteStudent(int id);

    public int updateStudent(Student student);

    public int addStudent(Student student);

    public List<Student> queryStudentByName(String name);

    public int getStudentCount();

}

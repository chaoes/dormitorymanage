package me.wtclmy.project.service;

import me.wtclmy.project.mapper.StudentMapper;
import me.wtclmy.project.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;
    @Override

    public String getStudentNamebyId(int id) {
        return studentMapper.queryStudentById(id).getStudentName();
    }

    @Override
    public List<Student> queryAllStudent() {
        return studentMapper.queryAllStudent();
    }

    @Override
    public Student queryStudentById(int id) {
        return studentMapper.queryStudentById(id);
    }

    @Override
    public List<Student> queryStudentByDormitoryId(int dormitoryId) {
        return studentMapper.queryStudentByDormitoryId(dormitoryId);
    }

    @Override
    @Transactional
    public int deleteStudent(int id) {
        return studentMapper.deleteStudent(id);
    }

    @Override
    @Transactional
    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    @Transactional
    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    @Transactional
    public List<Student> queryStudentByName(String name) {
        return studentMapper.queryStudentByName(name);
    }

    @Override
    public int getStudentCount() {
        return studentMapper.queryAllStudent().size();
    }
}

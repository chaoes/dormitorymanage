package me.wtclmy.project.service;

import me.wtclmy.project.mapper.ManagerMapper;
import me.wtclmy.project.mapper.StudentMapper;
import me.wtclmy.project.pojo.Manager;
import me.wtclmy.project.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/

@Service
public class LoginServiceImpl implements LoginService{
    public static final int USER_TYPE_STUDENT = 0;
    public static final int USER_TYPE_MANAGER = 1;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public boolean checkLogin(int usertype, int id, String password) {
        if(usertype==USER_TYPE_STUDENT){
            Student student= studentMapper.queryStudentById(id);
            if(null == student){
                return false;
            }
            if(password.equals(student.getStudentPassword())){
                return true;
            }
        }else if(usertype==USER_TYPE_MANAGER){
            Manager manager = managerMapper.queryManagerById(id);
            if(null == manager){
                return false;
            }
            if(password.equals(manager.getManagerPassword())){
                return true;
            }
        }
        return false;
    }
}

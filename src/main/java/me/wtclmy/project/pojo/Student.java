package me.wtclmy.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer studentId;         //学生id
    private String studentName;        //学生姓名
    private String studentPassword;    //学生密码
    private String studentPhone;       //学生电话
    private Dormitory dormitory;       //学生宿舍
}

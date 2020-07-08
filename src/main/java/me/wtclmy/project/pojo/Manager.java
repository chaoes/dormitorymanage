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
public class Manager {
    private Integer managerId;      //管理员id
    private String managerName;     //管理员姓名
    private String managerPassword;  //管理员密码
    private String managerPhone;     //管理员电话
}

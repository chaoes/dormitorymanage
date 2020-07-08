package me.wtclmy.project.service;

import me.wtclmy.project.pojo.Dormitory;


import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/7
 **/


public interface DormitoryService {

    public List<Dormitory> queryAllDormitory();    //查询全部宿舍


    public Dormitory queryDormitoryById(int id);   //根据id查询宿舍


    public int deleteDormitory(int id);     //删除宿舍


    public int addDormitory(Dormitory dormitory);    //增加宿舍

    public int getDormitoryCount();

}

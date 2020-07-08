package me.wtclmy.project.service;

import me.wtclmy.project.pojo.Manager;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/7
 **/


public interface ManagerService {

    public List<Manager> queryAllManager();


    public Manager queryManagerById(int id);


    public int deleteManager(int id);


    public int updateManager(Manager manager);


    public int addManager(Manager manager);

    public int getManagerCount();

    public String getManagerNameById(int id);

}

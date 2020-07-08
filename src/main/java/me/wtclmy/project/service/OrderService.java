package me.wtclmy.project.service;

import me.wtclmy.project.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/7
 **/


public interface OrderService {

    public List<Order> queryAllOrder();

    public List<Order> queryUnfinishOrder();

    public List<Order> queryFinishedOrder();

    public Order queryOrderById(int id);

    public List<Order> queryOrderByDormitoryId(int dormitoryId);

    public int deleteOrder(int id);

    public int updateOrder(Order order);

    public int addOrder(Order order);

    public int getOrderCount();

    public int getUnfinishOrderCount();

    public int getFinishedOrderCount();


}

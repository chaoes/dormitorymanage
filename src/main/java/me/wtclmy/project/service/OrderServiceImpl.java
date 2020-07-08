package me.wtclmy.project.service;

import me.wtclmy.project.mapper.OrderMapper;
import me.wtclmy.project.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/7
 **/

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> queryAllOrder() {
        return this.orderMapper.queryAllOrder();
    }

    @Override
    public List<Order> queryUnfinishOrder() {
        return this.orderMapper.queryUnfinishOrder();
    }

    @Override
    public List<Order> queryFinishedOrder() {
        return orderMapper.queryFinishedOrder();
    }

    @Override
    public Order queryOrderById(int id) {
        return this.orderMapper.queryOrderById(id);
    }

    @Override
    public List<Order> queryOrderByDormitoryId(int dormitoryId) {
        return this.orderMapper.queryOrderByDormitoryId(dormitoryId);
    }

    @Override
    @Transactional
    public int deleteOrder(int id) {
        return this.orderMapper.deleteOrder(id);
    }

    @Override
    @Transactional
    public int updateOrder(Order order) {
        return this.orderMapper.updateOrder(order);
    }

    @Override
    @Transactional
    public int addOrder(Order order) {
        return this.orderMapper.addOrder(order);
    }

    @Override
    public int getOrderCount() {
        return orderMapper.queryAllOrder().size();
    }

    @Override
    public int getUnfinishOrderCount() {
        return orderMapper.queryUnfinishOrder().size();
    }

    @Override
    public int getFinishedOrderCount() {
        return orderMapper.queryFinishedOrder().size();
    }
}

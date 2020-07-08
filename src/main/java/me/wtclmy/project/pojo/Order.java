package me.wtclmy.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/



public class Order {
    private Integer orderId;        //订单id

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Date getOrderStart() {
        return orderStart;
    }

    public void setOrderStart(Date orderStart) {
        this.orderStart = orderStart;
    }

    public Date getOrderFinish() {
        return orderFinish;
    }

    public void setOrderFinish(Date orderFinish) {
        this.orderFinish = orderFinish;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderInfo='" + orderInfo + '\'' +
                ", orderStart=" + orderStart +
                ", orderFinish=" + orderFinish +
                ", orderIsFinish=" + orderIsFinish +
                ", dormitory=" + dormitory +
                '}';
    }

    public Order() {
        this.orderStart = new Date();
        this.orderFinish = new Date(0001-1900,1-1,1);
        this.orderIsFinish = 0;
    }

    public Order(Integer orderId, String orderInfo, Dormitory dormitory) {
        this.orderId = orderId;
        this.orderInfo = orderInfo;
        this.dormitory = dormitory;
        this.orderStart = new Date();
        this.orderIsFinish = 0;
    }

    public void setFinishDate(Date date){
        this.orderFinish = date;
    }

    public void setIsFinished(){
        this.orderIsFinish=1;
    }

    public  void setIsUnfinish(){
        this.orderIsFinish = 0;
    }

    public Integer getOrderIsFinish() {
        return orderIsFinish;
    }

    public void setOrderIsFinish(Integer orderIsFinish) {
        this.orderIsFinish = orderIsFinish;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void finishThisOrder(){
        this.setFinishDate(new Date());
        this.setIsFinished();
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    private String orderInfo;       //订单信息
    private Date orderStart;        //订单开始时间
    private Date orderFinish;       //订单结束时间
    private Integer orderIsFinish;  //订单是否结束 0为未结束 1为结束
    private Dormitory dormitory;    //订单所属宿舍
}

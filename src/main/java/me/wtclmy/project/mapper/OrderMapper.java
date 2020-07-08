package me.wtclmy.project.mapper;

import me.wtclmy.project.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/

@Mapper
@Repository
public interface OrderMapper {

    @Select("SELECT * FROM `order`")
    @Results(id = "orderMapperall",value = {
            @Result(id = true,column = "orderId",property = "orderId"),
            @Result(column = "orderInfo",property = "orderInfo"),
            @Result(column = "orderStart",property = "orderStart"),
            @Result(column = "orderFinish",property = "orderFinish"),
            @Result(column = "orderIsFinish",property = "orderIsFinish"),
            @Result(column = "orderDormitoryId",property = "dormitory",
                    one = @One(
                            select = "me.wtclmy.project.mapper.DormitoryMapper.queryDormitoryById",
                            fetchType = FetchType.LAZY
                    ))
    })
    public List<Order> queryAllOrder();

    @Select("SELECT * FROM `order` WHERE orderIsFinish <>1")
    @Results(id = "orderMapperunfinish",value = {
            @Result(id = true,column = "orderId",property = "orderId"),
            @Result(column = "orderInfo",property = "orderInfo"),
            @Result(column = "orderStart",property = "orderStart"),
            @Result(column = "orderFinish",property = "orderFinish"),
            @Result(column = "orderIsFinish",property = "orderIsFinish"),
            @Result(column = "orderDormitoryId",property = "dormitory",
                    one = @One(
                            select = "me.wtclmy.project.mapper.DormitoryMapper.queryDormitoryById",
                            fetchType = FetchType.LAZY
                    ))
    })
    public List<Order> queryUnfinishOrder();

    @Select("SELECT * FROM `order` WHERE orderIsFinish = 1")
    @Results(id = "orderMapperfinished",value = {
            @Result(id = true,column = "orderId",property = "orderId"),
            @Result(column = "orderInfo",property = "orderInfo"),
            @Result(column = "orderStart",property = "orderStart"),
            @Result(column = "orderFinish",property = "orderFinish"),
            @Result(column = "orderIsFinish",property = "orderIsFinish"),
            @Result(column = "orderDormitoryId",property = "dormitory",
                    one = @One(
                            select = "me.wtclmy.project.mapper.DormitoryMapper.queryDormitoryById",
                            fetchType = FetchType.LAZY
                    ))
    })
    public List<Order> queryFinishedOrder();

    @Select("SELECT * FROM `order` WHERE orderId = #{id}")
    @Results(id = "orderMapperbyid",value = {
            @Result(id = true,column = "orderId",property = "orderId"),
            @Result(column = "orderInfo",property = "orderInfo"),
            @Result(column = "orderStart",property = "orderStart"),
            @Result(column = "orderFinish",property = "orderFinish"),
            @Result(column = "orderIsFinish",property = "orderIsFinish"),
            @Result(column = "orderDormitoryId",property = "dormitory",
            one = @One(
                    select = "me.wtclmy.project.mapper.DormitoryMapper.queryDormitoryById",
                    fetchType = FetchType.LAZY
            ))
    })
    public Order queryOrderById(@Param("id") int id);

    @Select("SELECT * FROM `order` WHERE orderDormitoryId = #{dormitoryId}")
    @Results(id = "order_dormitory",value = {
            @Result(id = true,column = "orderId",property = "orderId"),
            @Result(column = "orderInfo",property = "orderInfo"),
            @Result(column = "orderStart",property = "orderStart"),
            @Result(column = "orderFinish",property = "orderFinish"),
            @Result(column = "orderIsFinish",property = "orderIsFinish"),
            @Result(column = "orderDormitoryId",property = "dormitory",
                    one = @One(
                            select = "me.wtclmy.project.mapper.DormitoryMapper.queryDormitoryById",
                            fetchType = FetchType.LAZY
                    ))
    })
    public List<Order> queryOrderByDormitoryId(@Param("dormitoryId") int dormitoryId);


    @Delete("DELETE FROM `order` WHERE orderId = #{id}")
    public int deleteOrder(int id);

    @Update("UPDATE `order` set orderInfo = #{orderInfo},orderStart=#{orderStart},orderFinish=#{orderFinish},orderIsFinish=#{orderIsFinish},orderDormitoryId=#{dormitory.dormitoryId} where orderId = #{orderId} ")
    public int updateOrder(Order order);

    @Insert("INSERT INTO `order`(orderInfo,orderStart,orderFinish,orderIsFinish,orderDormitoryId) VALUES(#{orderInfo},#{orderStart},#{orderFinish},#{orderIsFinish},#{dormitory.dormitoryId})")
    public int addOrder(Order order);

}

package me.wtclmy.project.mapper;

import me.wtclmy.project.pojo.Dormitory;
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
public interface DormitoryMapper {

    @Select("SELECT * FROM dormitory")
    public List<Dormitory> queryAllDormitory();    //查询全部宿舍

    @Select("SELECT * FROM dormitory WHERE dormitoryId = #{id}")
    public Dormitory queryDormitoryById(@Param("id") int id);   //根据id查询宿舍

    @Delete("DELETE FROM dormitory WHERE dormitoryId = #{id}")
    public int deleteDormitory(@Param("id") int id);            //删除宿舍

    @Insert("INSERT INTO dormitory(dormitoryId) VALUES(#{dormitoryId})")
    public int addDormitory(Dormitory dormitory);    //增加宿舍

}

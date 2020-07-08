package me.wtclmy.project.mapper;

import me.wtclmy.project.pojo.Manager;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/

@Mapper
@Repository
public interface ManagerMapper {

    @Select("select * from manager")
    public List<Manager> queryAllManager();

    @Select("select * from manager where managerId = #{id}")
    public Manager queryManagerById(@Param("id") int id);

    @Delete("DELETE FROM manager WHERE managerId = #{id}")
    public int deleteManager(@Param("id") int id);

    @Update("UPDATE manager set managerName = #{managerName},managerPassword=#{managerPassword},managerPhone=#{managerPhone} where managerId = #{managerId} ")
    public int updateManager(Manager manager);

    @Insert("insert into manager(managerName,managerPassword,managerPhone) values(#{managerName},#{managerPassword},#{managerPhone})")
    public int addManager(Manager manager);
}

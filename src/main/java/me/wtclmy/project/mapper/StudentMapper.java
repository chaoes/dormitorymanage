package me.wtclmy.project.mapper;

import me.wtclmy.project.pojo.Student;
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
public interface StudentMapper {

    @Select("SELECT * FROM student")
    @Results(id="studentMapAll",value = {
            @Result(id = true,column = "studentId" ,property = "studentId"),
            @Result(column = "studentName",property = "studentName"),
            @Result(column = "studentPassword",property = "studentPassword"),
            @Result(column = "studentPhone",property = "studentPhone"),
            @Result(column = "studentDormitoryId",property = "dormitory",
                    one = @One(
                            select = "me.wtclmy.project.mapper.DormitoryMapper.queryDormitoryById",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    public List<Student> queryAllStudent();

    @Select("SELECT * FROM student WHERE studentId = #{id}")
    @Results(id="studentMap",value = {
            @Result(id = true,column = "studentId" ,property = "studentId"),
            @Result(column = "studentName",property = "studentName"),
            @Result(column = "studentPassword",property = "studentPassword"),
            @Result(column = "studentPhone",property = "studentPhone"),
            @Result(column = "studentDormitoryId",property = "dormitory",
            one = @One(
                    select = "me.wtclmy.project.mapper.DormitoryMapper.queryDormitoryById",
                    fetchType = FetchType.LAZY
            )
            )
    })
    public Student queryStudentById(@Param("id") int id);

    @Select("SELECT * FROM student WHERE studentName = #{name}")
    @Results(id="student_Name",value = {
            @Result(id = true,column = "studentId" ,property = "studentId"),
            @Result(column = "studentName",property = "studentName"),
            @Result(column = "studentPassword",property = "studentPassword"),
            @Result(column = "studentPhone",property = "studentPhone"),
            @Result(column = "studentDormitoryId",property = "dormitory",
                    one = @One(
                            select = "me.wtclmy.project.mapper.DormitoryMapper.queryDormitoryById",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    public List<Student> queryStudentByName(String name);

    @Select("SELECT * FROM student WHERE studentDormitoryId = #{dormitoryId}")
    @Results(id = "student_dormitory",value = {
            @Result(id = true,column = "studentId" ,property = "studentId"),
            @Result(column = "studentName",property = "studentName"),
            @Result(column = "studentPhone",property = "studentPhone"),
            @Result(column = "studentDormitoryId",property = "dormitory",
                    one = @One(
                            select = "me.wtclmy.project.mapper.DormitoryMapper.queryDormitoryById",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    public List<Student> queryStudentByDormitoryId(@Param("dormitoryId") int dormitoryId);

    @Delete("DELETE FROM student WHERE studentId = #{id}")
    public int deleteStudent(int id);

    @Update("UPDATE student set studentName = #{studentName},studentPassword=#{studentPassword},studentDormitoryId=#{dormitory.dormitoryId},studentPhone=#{studentPhone} where studentId = #{studentId} ")
    public int updateStudent(Student student);

    @Insert("insert into student(studentName,studentPassword,studentDormitoryId,studentPhone) values(#{studentName},#{studentPassword},#{dormitory.dormitoryId},#{studentPhone})")
    public int addStudent(Student student);

}

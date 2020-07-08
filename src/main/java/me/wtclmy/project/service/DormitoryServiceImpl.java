package me.wtclmy.project.service;

import me.wtclmy.project.mapper.DormitoryMapper;
import me.wtclmy.project.pojo.Dormitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/7
 **/

@Service
public class DormitoryServiceImpl implements DormitoryService{
    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Override
    public List<Dormitory> queryAllDormitory() {
        return dormitoryMapper.queryAllDormitory();
    }

    @Override
    public Dormitory queryDormitoryById(int id) {
        return dormitoryMapper.queryDormitoryById(id);
    }

    @Override
    @Transactional
    public int deleteDormitory(int id){
        return dormitoryMapper.deleteDormitory(id);
    }

    @Override
    @Transactional
    public int addDormitory(Dormitory dormitory){
        return dormitoryMapper.addDormitory(dormitory);
    }

    @Override
    public int getDormitoryCount() {
        return dormitoryMapper.queryAllDormitory().size();
    }
}

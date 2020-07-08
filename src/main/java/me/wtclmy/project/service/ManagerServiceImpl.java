package me.wtclmy.project.service;

import me.wtclmy.project.mapper.ManagerMapper;
import me.wtclmy.project.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/7
 **/

@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> queryAllManager() {
        return this.managerMapper.queryAllManager();
    }

    @Override
    public Manager queryManagerById(int id) {
        return this.managerMapper.queryManagerById(id);
    }

    @Override
    @Transactional
    public int deleteManager(int id) {
        return this.managerMapper.deleteManager(id);
    }

    @Override
    @Transactional
    public int updateManager(Manager manager) {
        return this.managerMapper.updateManager(manager);
    }

    @Override
    @Transactional
    public int addManager(Manager manager) {
        return this.managerMapper.addManager(manager);
    }

    @Override
    public int getManagerCount() {
        return managerMapper.queryAllManager().size();
    }

    @Override
    public String getManagerNameById(int id) {
        return managerMapper.queryManagerById(id).getManagerName();
    }
}

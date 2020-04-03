package cn.qing.tian.test.service.impl;

import cn.qing.tian.test.entity.shiroEntity.Emp;
import cn.qing.tian.test.entity.shiroEntity.Permission;
import cn.qing.tian.test.entity.shiroEntity.Role;
import cn.qing.tian.test.mapper.UserMapper;
import cn.qing.tian.test.service.UserService;
import cn.qing.tian.test.utils.Md5Util;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper um;

    public UserServiceImpl(UserMapper um) {
        this.um = um;
    }

    @Override
    public Emp queryOne(String userName) {
        return um.queryOne(userName);
    }

    @Override
    public List<Role> queryRole(int roleId) {
        return um.queryRole(roleId);
    }

    @Override
    public List<Permission> queryPermission(int perId) {
        return um.queryPermission(perId);
    }

    @Override
    public int addUser(Emp emp) {
        try {
            String word = emp.getPassWord();
            Md5Util md5Util = new Md5Util();
            String de = md5Util.md5Salt(word);
            emp.setPassWord(de);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return um.addUser(emp);
    }

    @Override
    public int deleteUser(int id) {
        return um.deleteUser(id);
    }
}

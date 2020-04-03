package cn.qing.tian.test.service;


import cn.qing.tian.test.entity.shiroEntity.Emp;
import cn.qing.tian.test.entity.shiroEntity.Permission;
import cn.qing.tian.test.entity.shiroEntity.Role;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    public Emp queryOne(String userName);

    public List<Role> queryRole(int roleId);

    public List<Permission> queryPermission(int perId);

    public int addUser(Emp emp) throws NoSuchAlgorithmException;

    //删除用户信息
    public int deleteUser(int id);
}

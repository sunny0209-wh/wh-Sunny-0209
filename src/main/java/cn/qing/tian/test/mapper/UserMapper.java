package cn.qing.tian.test.mapper;

import cn.qing.tian.test.entity.shiroEntity.Emp;
import cn.qing.tian.test.entity.shiroEntity.Permission;
import cn.qing.tian.test.entity.shiroEntity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {
    //查询用户名，密码
    Emp queryOne(String userName);

    //查询角色信息
    List<Role> queryRole(int roleId);

    //查询权限信息
    List<Permission> queryPermission(int perId);

    //增加用户信息
    int addUser(Emp emp);

    //删除用户信息
    int deleteUser(int id);
}

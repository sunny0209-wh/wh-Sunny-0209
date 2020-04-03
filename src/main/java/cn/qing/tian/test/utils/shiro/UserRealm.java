package cn.qing.tian.test.utils.shiro;

import cn.qing.tian.test.entity.shiroEntity.Emp;
import cn.qing.tian.test.entity.shiroEntity.Permission;
import cn.qing.tian.test.entity.shiroEntity.Role;
import cn.qing.tian.test.service.impl.UserServiceImpl;
import cn.qing.tian.test.utils.StringUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//登录验证及权限授权
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl usi;

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    //认证权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info(("---------权限配置~授权---------"));
        //获取当前登录用户名
        //String userName = principalCollection.getPrimaryPrincipal().toString();
        Emp emp = (Emp)principalCollection.getPrimaryPrincipal();
        String userName = emp.getUserName();
        //查询数据库里用户授权内容
        int preid = 0;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        try {
            List<Role> roles =  usi.queryRole((int)emp.getRoleId());
            for (Role role: roles) {
                preid = (int)role.getPerId();
                simpleAuthorizationInfo.addRole(role.getRoleName());
            }
            List<Permission> permissions = usi.queryPermission(preid);
            for (Permission permission: permissions) {
                simpleAuthorizationInfo.addStringPermission(permission.getPerName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return simpleAuthorizationInfo;
    }

    //认证身份
    /*
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份 如果返回一个SimpleAccount
     * 对象则认证通过，如果返回值为空或者异常，则认证不通过。 1、检查提交的进行认证的令牌信息 2、根据令牌信息从数据源(通常为数据库)中获取用户信息
     * 3、对用户信息进行匹配验证 4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例
     * 5、验证失败则抛出AuthenticationException异常信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        logger.info("----------用户验证--------");
        //判断用户名和密码
        //获取用户输入的账号
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        Emp emp = usi.queryOne(token.getUsername());
        //判断用户名
        if(StringUtil.IsNullOrEmptyT(emp.toString()))
        {
            //用户名不存在，shiro底层会抛出UnKnowAccountException异常
            return null;
        }
        //判断密码
            //1.需要返回给login的数据
            //2.数据库的密码
            //3.shiro的名字
        return new SimpleAuthenticationInfo(emp,emp.getPassWord(),getName());
    }
}

package cn.qing.tian.test.controller.shiroController;

import cn.qing.tian.test.entity.shiroEntity.Emp;
import cn.qing.tian.test.service.impl.RedisService;
import cn.qing.tian.test.service.impl.UserServiceImpl;
import cn.qing.tian.test.utils.shiro.UserRealm;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shiro")
public class ShiroController {
    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);
    public ShiroController(UserServiceImpl usi, RedisService rs) {
        this.usi = usi;
        this.rs = rs;
    }
    private final UserServiceImpl usi;
    private final RedisService rs;
    private String userNames = null;
    //登录验证
    @GetMapping("/denglu")
    public String dengLu(String userName,String passWord,boolean rememberme)
    {
        System.out.println("----------"+rememberme);
        //封装用户的登录数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,passWord);
        //获取当前登录用户对象
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            subject.login(usernamePasswordToken);//执行登录方法，如果没有出现异常就代表登录成功。
            if(rememberme)
            {
                userNames = userName;
                if(userNames != null && rs.get(userNames) == null)
                {
                    rs.setTime(userNames,86400,passWord);
                }
            }
            else
            {
                rs.del(userNames);
            }
            Session token = subject.getSession();
            result.put("userName",userName);
            result.put("token",token.getId());
            result.put("status","200");
            result.put("msg","验证成功");
        } catch (Exception e) {
            //instanceof 用于判断 UnknownAccountException 是否是 Exception 的实例
            if(e instanceof UnknownAccountException)
            {
                logger.info("账号不存在：UnknownAccountException");
                result.put("status","400");
                result.put("msg","登录失败！账号不存在");
            }
            else if (e instanceof IncorrectCredentialsException)
            {
                logger.info("账号不正确：IncorrectCredentialsException");
                result.put("status","401");
                result.put("msg","登录失败，密码不正确");
            }
            else
            {
                logger.info("else -- >" + e);
                result.put("status","502");
                result.put("msg","登录失败，发生未知错误：" + e);
            }
        }
        logger.info(result.toString());
        return JSON.toJSONString(result);
    }

    @GetMapping("/rememberi")
    public String remrmber()
    {
        Map<String,Object> result = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        Session token = subject.getSession();
        if(userNames != null)
        {
            result.put("rememberme",true);
            result.put("token",token.getId());
            result.put("userName",userNames);
            result.put("passWord",rs.get(userNames));
        }
        return JSON.toJSONString(result);
    }

    //增加
    @PostMapping("/add")
    public String addUser(Emp emp) {
        return JSON.toJSONString(usi.addUser(emp));
    }

    //删除
    @DeleteMapping("/delete")
    public String deleteUser(int id) {
        return JSON.toJSONString(usi.deleteUser(id));
    }
}

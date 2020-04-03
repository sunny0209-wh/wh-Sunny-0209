package cn.qing.tian.test.controller;

import cn.qing.tian.test.service.impl.RedisService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 16643 on 2019/11/4.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService rs;

    @Autowired
    public RedisController(RedisService redisService) {
        this.rs = redisService;
    }

    @PostMapping("/set")
    public String setRedis(String key, String value) {
        return JSON.toJSON(rs.set(key, value)).toString();
    }

    @PostMapping("/get")
    public String getRedis(String key) {
        return JSON.toJSON(rs.get(key)).toString();
    }

    @PostMapping("headerTest")
    public String haha(String name,String phone)
    {
        boolean ccc = false;
        if(name.equals("wanghu") && phone.equals("123456"))
        {
            ccc = true;
        }
        return JSON.toJSONString(ccc);
    }
}

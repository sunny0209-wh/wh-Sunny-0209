package cn.qing.tian.test.controller;

import cn.qing.tian.test.service.impl.SmsServiceImpl;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 16643 on 2019/11/5.
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    private final SmsServiceImpl sme;

    @Autowired
    public SmsController(SmsServiceImpl sme) {
        this.sme = sme;
    }

    @PostMapping("/send")
    public String iphone(String userName) {
        return JSON.toJSON(sme.sendSms(userName)).toString();
    }

    @PostMapping("/testing")
    public String testing(String userName, String value) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> result = new HashMap<String, Object>();
        Session token = subject.getSession();//获取sessionId
        boolean values = sme.SmsRedis(userName, value);
        result.put("token",token.getId());
        result.put("jgValue",values);
        return JSON.toJSON(result).toString();
    }

}

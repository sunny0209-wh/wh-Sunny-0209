package cn.qing.tian.test.service.impl;

import cn.qing.tian.test.service.SmsService;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
/**
 * Created by 16643 on 2019/11/5.
 */
@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.accessSecret}")
    private String accessSecret;

    @Value("${aliyun.sms.signName}")
    private String signName;

    @Value("${aliyun.sms.templateCode}")
    private String templateCode;

    @Autowired
    public SmsServiceImpl(RedisService reds, EmpServiceImpl esi) {
        this.reds = reds;
        this.esi = esi;
    }

    private String randCode = null;

    @Override
    public boolean sendSms(String iPoneNumber) {
        //DefaultProfile 和 IAcsClient 是创建DefaultAcsClient实例并初始化。
        // 三个参数分别对应的是:地域ID，RAM账号的AccessKey ID， RAM账号AccessKey Secret
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", iPoneNumber);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        JSONObject object = new JSONObject();
        randCode = getRandCode(6);
        logger.info("验证码为：{}", randCode);
        object.put("code", randCode);
        request.putQueryParameter("TemplateParam", object.toJSONString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info(response.getData());
            return true;
        } catch (Exception e) {
            logger.error("{}", e);
        }
        return false;
    }

    /**
     * 生成随机验证码
     */
    private static String getRandCode(int digits) {
        StringBuilder sBuilder = new StringBuilder();
        Random rd = new Random((new Date()).getTime());

        for (int i = 0; i < digits; ++i) {
            sBuilder.append(rd.nextInt(9));
        }
        return sBuilder.toString();
    }

    private final RedisService reds;
    private final EmpServiceImpl esi;

    private String setSmsRedis(String userName) {
        int code = 300;
        String value = randCode;
        String good = reds.setTime(userName, code, value);
        return good;
    }

    public boolean SmsRedis(String userName, String value) {
        boolean result = false;
        String sets = setSmsRedis(userName);
        System.out.println(userName);
        if(esi.queryName(userName) !=1)
        {
            esi.addUserName(userName);
        }
        if (sets.equals("OK"))
        {
            String value1 = reds.get(userName);
            if (value1 == null)
            {
                return false;
            }
            else
            {
                if (value.equals(value1))
                {
                    result = true;
                }
            }
        }
        return result;
    }
}

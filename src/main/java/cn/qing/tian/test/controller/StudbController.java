package cn.qing.tian.test.controller;

import cn.qing.tian.test.service.impl.StudbServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 16643 on 2019/9/25.
 */
@RestController
@RequestMapping("/three")
public class StudbController {

    private final StudbServiceImpl ssi;

    @Autowired
    public StudbController(StudbServiceImpl ssi) {
        this.ssi = ssi;
    }

    @GetMapping("/province")
    public String queryPrivince() {
        return JSON.toJSON(ssi.queryProvince()).toString();
    }

    @GetMapping("/city")
    public String queryCity(int provinceid) {
        return JSON.toJSON(ssi.queryCity(provinceid)).toString();
    }

    @GetMapping("/county")
    public String queryCounty(int cityid) {
        return JSON.toJSON(ssi.queryCounty(cityid)).toString();
    }

    @GetMapping("/stu")
    public String queryStu(int cityid) {
        return JSON.toJSON(ssi.queryStu(cityid)).toString();
    }
}

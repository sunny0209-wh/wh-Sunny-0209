package cn.qing.tian.test.controller;

import cn.qing.tian.test.entity.shiroEntity.Emp;
import cn.qing.tian.test.service.impl.EmpServiceImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmpController {

    private final EmpServiceImpl es;

    @Autowired
    public EmpController(EmpServiceImpl es) {
        this.es = es;
    }

    @GetMapping("/page")
    public String page(int pageNo, int pageSize) {
        return JSON.toJSON(es.page(pageNo, pageSize)).toString();
    }

    @GetMapping("/one")
    public String queryFirst(String userName){
        return JSON.toJSONString(es.queryFirst(userName));
    }

    @PutMapping("/update")
    public String modifyEmp(Emp emp)
    {
        System.out.println(emp);
        System.out.println(es.modifyEmp(emp));
        return JSON.toJSONString(es.modifyEmp(emp));
    }

    @GetMapping("/www")
    public String all(){
        return JSON.toJSONString(es.all());
    }

    @GetMapping("/types")
    public String queryFunction_types()
    {
        return JSON.toJSONString(es.queryFunction_types());
    }

    @GetMapping("/page1")
    public String pages(int pageNo, int pageSize) {
        return JSON.toJSON(es.pages(pageNo, pageSize)).toString();
    }
}

package cn.qing.tian.test.service.impl;

import cn.qing.tian.test.entity.shiroEntity.Emp;
import cn.qing.tian.test.entity.shiroEntity.FunctionTypes;
import cn.qing.tian.test.entity.shiroEntity.VRP;
import cn.qing.tian.test.mapper.EmpMapper;
import cn.qing.tian.test.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 16643 on 2019/9/13.
 */
@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper em;

    @Autowired
    public EmpServiceImpl(EmpMapper em) {
        this.em = em;
    }

    @Override
    public List<VRP> queryAll() {
        return em.queryAll();
    }

    @Override
    public PageInfo<VRP> page(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<VRP> list = em.queryAll();
        //        System.out.println("总数量：" + pageInfo.getTotal());
//        System.out.println("当前页查询记录：" + pageInfo.getList().size());
//        System.out.println("当前页码：" + pageInfo.getPageNum());
//        System.out.println("每页显示数量：" + pageInfo.getPageSize());
//        System.out.println("总页：" + pageInfo.getPages());
        return new PageInfo<VRP>(list);
    }

    @Override
    public Emp queryFirst(String userName) {
        return em.queryFirst(userName);
    }

    @Override
    public int modifyEmp(Emp emp) {
        return em.modifyEmp(emp);
    }

    @Override
    public int updateImg(String headImg, String userName) {
        return em.updateImg(headImg,userName);
    }

    @Override
    public int queryName(String userName) {
        return em.queryName(userName);
    }

    @Override
    public int addUserName(String userName) {
        return em.addUserName(userName);
    }

    @Override
    public List<Emp> all() {
        return em.all();
    }

    @Override
    public List<FunctionTypes> queryFunction_types() {
        return em.queryFunction_types();
    }

    @Override
    public PageInfo<FunctionTypes> pages(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<FunctionTypes> list = em.queryFunction_types();
        return new PageInfo<FunctionTypes>(list);
    }
}

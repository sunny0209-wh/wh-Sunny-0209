package cn.qing.tian.test.service;

import cn.qing.tian.test.entity.shiroEntity.Emp;
import cn.qing.tian.test.entity.shiroEntity.FunctionTypes;
import cn.qing.tian.test.entity.shiroEntity.VRP;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by 16643 on 2019/9/14.
 */
public interface EmpService {
    public List<VRP> queryAll();
    public PageInfo<VRP> page(int pageNo, int pageSize);

    //查询单个
    public Emp queryFirst(String userName);

    //修改
    public int modifyEmp(Emp emp);

    public int updateImg(String headImg,String userName);

    public int queryName(String userName);

    public int addUserName(String userName);

    public List<Emp> all();

    public List<FunctionTypes> queryFunction_types();
    public PageInfo<FunctionTypes> pages(int pageNo, int pageSize);
}

package cn.qing.tian.test.mapper;

import cn.qing.tian.test.entity.shiroEntity.Emp;
import cn.qing.tian.test.entity.shiroEntity.FunctionTypes;
import cn.qing.tian.test.entity.shiroEntity.VRP;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by 16643 on 2019/9/13.
 */
@Repository
public interface EmpMapper {
    //分页
    Page<VRP> queryAll();

    PageInfo<VRP> page(int pageNo, int pageSize);

    //查询单个
    Emp queryFirst(String userName);

    //修改

    int modifyEmp(Emp emp);
    int updateImg(String headImg,String userName);

    int queryName(String userName);

    int addUserName(String userName);

    @Select("select * from emp")
    List<Emp> all();

    Page<FunctionTypes> queryFunction_types();
    PageInfo<FunctionTypes> pages(int pageNo, int pageSize);
}

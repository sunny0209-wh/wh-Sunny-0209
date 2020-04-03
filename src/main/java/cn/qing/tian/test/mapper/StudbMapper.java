package cn.qing.tian.test.mapper;

import cn.qing.tian.test.entity.studbEntity.City;
import cn.qing.tian.test.entity.studbEntity.County;
import cn.qing.tian.test.entity.studbEntity.Province;
import cn.qing.tian.test.entity.studbEntity.Zbstudent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 16643 on 2019/9/25.
 */
@Repository
public interface StudbMapper {

    //三级联查
    List<Province> queryProvince();

    List<City> queryCity(int provinceid);

    List<County> queryCounty(int cityid);

    //学员信息
    List<Zbstudent> queryStu(int cityid);
}

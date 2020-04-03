package cn.qing.tian.test.service;

import cn.qing.tian.test.entity.studbEntity.City;
import cn.qing.tian.test.entity.studbEntity.County;
import cn.qing.tian.test.entity.studbEntity.Province;
import cn.qing.tian.test.entity.studbEntity.Zbstudent;

import java.util.List;

/**
 * Created by 16643 on 2019/9/25.
 */
public interface StudbService {

    //三级联查
    public List<Province> queryProvince();

    public List<City> queryCity(int provinceId);

    public List<County> queryCounty(int cityId);

    public List<Zbstudent> queryStu(int cityId);
}

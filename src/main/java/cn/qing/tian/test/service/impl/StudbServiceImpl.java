package cn.qing.tian.test.service.impl;

import cn.qing.tian.test.entity.studbEntity.City;
import cn.qing.tian.test.entity.studbEntity.County;
import cn.qing.tian.test.entity.studbEntity.Province;
import cn.qing.tian.test.entity.studbEntity.Zbstudent;
import cn.qing.tian.test.mapper.StudbMapper;
import cn.qing.tian.test.service.StudbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 16643 on 2019/9/25.
 */
@Service
public class StudbServiceImpl implements StudbService {

    private final StudbMapper sm;

    @Autowired
    public StudbServiceImpl(StudbMapper sm) {
        this.sm = sm;
    }

    @Override
    public List<Province> queryProvince() {
        return sm.queryProvince();
    }

    @Override
    public List<City> queryCity(int provinceid) {
        return sm.queryCity(provinceid);
    }

    @Override
    public List<County> queryCounty(int cityid) {
        return sm.queryCounty(cityid);
    }

    @Override
    public List<Zbstudent> queryStu(int cityid) {
        return sm.queryStu(cityid);
    }
}

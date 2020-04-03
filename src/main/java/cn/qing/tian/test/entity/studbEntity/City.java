package cn.qing.tian.test.entity.studbEntity;

public class City {
    private Long cityid;
    private String cityname;
    private Long provinceid;

    public Long getCityid() {
        return cityid;
    }

    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Long getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityid=" + cityid +
                ", cityname='" + cityname + '\'' +
                ", provinceid=" + provinceid +
                '}';
    }
}

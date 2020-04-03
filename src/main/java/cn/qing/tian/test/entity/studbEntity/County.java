package cn.qing.tian.test.entity.studbEntity;

public class County {
    private Long countyid;
    private String countyname;
    private Long cityid;

    public Long getCountyid() {
        return countyid;
    }

    public void setCountyid(Long countyid) {
        this.countyid = countyid;
    }

    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public Long getCityid() {
        return cityid;
    }

    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }

    @Override
    public String toString() {
        return "County{" +
                "countyid=" + countyid +
                ", countyname='" + countyname + '\'' +
                ", cityid=" + cityid +
                '}';
    }
}

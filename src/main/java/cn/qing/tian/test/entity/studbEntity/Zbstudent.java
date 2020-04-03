package cn.qing.tian.test.entity.studbEntity;

public class Zbstudent {
    private Long stuid;
    private String stuname;
    private String phone;
    private Long gender;
    private Long age;
    private Long provinceid;
    private Long cityid;
    private Long countyid;
    private String address;

    public Long getStuid() {
        return stuid;
    }

    public void setStuid(Long stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    public Long getCityid() {
        return cityid;
    }

    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }

    public Long getCountyid() {
        return countyid;
    }

    public void setCountyid(Long countyid) {
        this.countyid = countyid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Zbstudent(Long stuid, String stuname, String phone, Long gender, Long age, Long provinceid, Long cityid, Long countyid, String address) {
        this.stuid = stuid;
        this.stuname = stuname;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.provinceid = provinceid;
        this.cityid = cityid;
        this.countyid = countyid;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Zbstudent{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", provinceid=" + provinceid +
                ", cityid=" + cityid +
                ", countyid=" + countyid +
                ", address='" + address + '\'' +
                '}';
    }
}

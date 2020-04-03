package cn.qing.tian.test.entity.studbEntity;

public class Province {
    private Long provinceid;
    private String provincename;

    public Long getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provinceid=" + provinceid +
                ", provincename='" + provincename + '\'' +
                '}';
    }
}

package cn.qing.tian.test.entity.shiroEntity;


public class Permission {

  private long id;
  private long perId;
  private String perName;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPerId() {
    return perId;
  }

  public void setPerId(long perId) {
    this.perId = perId;
  }


  public String getPerName() {
    return perName;
  }

  public void setPerName(String perName) {
    this.perName = perName;
  }

  @Override
  public String toString() {
    return "Permission{" +
            "id=" + id +
            ", perId=" + perId +
            ", perName='" + perName + '\'' +
            '}';
  }
}

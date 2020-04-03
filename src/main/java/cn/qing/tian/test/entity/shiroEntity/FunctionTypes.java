package cn.qing.tian.test.entity.shiroEntity;


public class FunctionTypes {

  private long funId;
  private String funName;
  private long departmentId;


  public long getFunId() {
    return funId;
  }

  public void setFunId(long funId) {
    this.funId = funId;
  }


  public String getFunName() {
    return funName;
  }

  public void setFunName(String funName) {
    this.funName = funName;
  }


  public long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(long departmentId) {
    this.departmentId = departmentId;
  }

  @Override
  public String toString() {
    return "FunctionTypes{" +
            "funId=" + funId +
            ", funName='" + funName + '\'' +
            ", departmentId=" + departmentId +
            '}';
  }
}

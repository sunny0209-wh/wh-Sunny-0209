package cn.qing.tian.test.entity.shiroEntity;


public class Role {

  private long id;
  private long roleId;
  private String roleName;
  private long perId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public long getPerId() {
    return perId;
  }

  public void setPerId(long perId) {
    this.perId = perId;
  }

  @Override
  public String toString() {
    return "Role{" +
            "id=" + id +
            ", roleId=" + roleId +
            ", roleName='" + roleName + '\'' +
            ", perId=" + perId +
            '}';
  }
}

package cn.qing.tian.test.entity.shiroEntity;


public class VRP {

  private long id;
  private String name;
  private String gender;
  private long age;
  private String userName;
  private String userMail;
  private String passWord;
  private String roleName;
  private String perName;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserMail() {
    return userMail;
  }

  public void setUserMail(String userMail) {
    this.userMail = userMail;
  }


  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public String getPerName() {
    return perName;
  }

  public void setPerName(String perName) {
    this.perName = perName;
  }

  @Override
  public String toString() {
    return "VRP{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", age=" + age +
            ", userName='" + userName + '\'' +
            ", userMail='" + userMail + '\'' +
            ", passWord='" + passWord + '\'' +
            ", roleName='" + roleName + '\'' +
            ", perName='" + perName + '\'' +
            '}';
  }
}

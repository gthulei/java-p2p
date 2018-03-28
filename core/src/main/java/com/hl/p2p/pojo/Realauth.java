package com.hl.p2p.pojo;

public class Realauth extends BaseAuth{

  private String realname;//真实姓名

  private int sex = 0; //性别

  private String birthdate;//生日

  private String idnumber;//身份证号码

  public Realauth() {
  }

  private String address;//身份证地址

  private String image1;//身份证正面照

  public void setAddress(String address) {
    this.address = address;
  }

  private String image2;//身份证反面照

  public Realauth(int state, Logininfo applier) {
    super(state, applier);
  }

  public String getGender() {
    return this.sex == 0 ? "男" : "女";
  }

  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname == null ? null : realname.trim();
  }

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate == null ? null : birthdate.trim();
  }

  public String getIdnumber() {
    return idnumber;
  }

  public void setIdnumber(String idnumber) {
    this.idnumber = idnumber == null ? null : idnumber.trim();
  }

  public String getAddress() {
    return address;
  }


  public String getImage1() {
    return image1;
  }

  public void setImage1(String image1) {
    this.image1 = image1 == null ? null : image1.trim();
  }

  public String getImage2() {
    return image2;
  }

  public void setImage2(String image2) {
    this.image2 = image2 == null ? null : image2.trim();
  }


}
package com.eomcs.lms.domain;

import java.util.Date;

public class User {
  private int    personNum;
  private String personName;
  private String phoneNum;
  private String picture;
  private String passwd;
  private String email;
  private Date   joinDay;
  
  public int getPersonNum() {
    return personNum;
  }
  public void setPersonNum(int personNum) {
    this.personNum = personNum;
  }
  public String getPersonName() {
    return personName;
  }
  public void setPersonName(String personName) {
    this.personName = personName;
  }
  public String getPhoneNum() {
    return phoneNum;
  }
  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }
  public String getPicture() {
    return picture;
  }
  public void setPicture(String picture) {
    this.picture = picture;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Date getJoinDay() {
    return joinDay;
  }
  public void setJoinDay(Date joinDay) {
    this.joinDay = joinDay;
  }
  
  
}

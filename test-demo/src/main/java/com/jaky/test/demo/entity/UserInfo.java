package com.jaky.test.demo.entity;

/**
 * Created by jaky.wang on 2017/9/30.
 */
public class UserInfo {
  private long id;
  private String name;
  private String password;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserInfo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}

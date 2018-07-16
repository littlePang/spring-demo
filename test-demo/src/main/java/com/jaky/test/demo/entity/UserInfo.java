package com.jaky.test.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jaky.wang on 2017/9/30.
 */
public class UserInfo {


  public  String classify(Set<?> s) {
    return "set";
  }
  public  String classify(Collection<?> s) {
    return "unknown";
  }
  public static void main(String[] args) {
    Collection<?>[] collections = {new HashSet<Integer>(), new ArrayList<Integer>()};
    for (Collection<?> c : collections) {
      System.out.println(new UserInfo().classify(c));
    }
  }


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

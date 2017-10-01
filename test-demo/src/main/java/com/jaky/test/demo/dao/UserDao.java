package com.jaky.test.demo.dao;

import com.jaky.test.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jaky.wang on 2017/9/30.
 */
@Mapper
public interface UserDao {

  @Select("select * from user_info where id=#{id}")
  UserInfo findByid(@Param("id") long id);

}

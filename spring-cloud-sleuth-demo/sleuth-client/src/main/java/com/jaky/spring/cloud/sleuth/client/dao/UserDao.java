package com.jaky.spring.cloud.sleuth.client.dao;

import com.jaky.spring.cloud.sleuth.client.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;

/**
 * Created by xiaomo.wj on 2018/1/31.
 */
@Mapper
public interface UserDao {

  @Select("select * from user_info where id=#{id}")
  @ContinueSpan
  UserInfo findById(@SpanTag("id") @Param("id") long id);

}


package com.xiaxin.mapper;


import com.xiaxin.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("SELECT userId ,NAME,AGE, createDate FROM users where userId=#{id}")
    List<Users> getUser(@Param("id") Long id);

    @Insert("insert into users values (null,#{name},#{age})")
    int addUser(Users users);
}

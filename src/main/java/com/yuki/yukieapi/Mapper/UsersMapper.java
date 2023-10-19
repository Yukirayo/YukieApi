package com.yuki.yukieapi.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper {
    @Select("select name from users where name=#{name}")
    String SelectName(String name);

    @Select("select password from users where name=#{name}")
    String SelectPassword(String name);
}

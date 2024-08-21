package com.heima.admin.dao;

import com.heima.model.admin.pojos.AdUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdUserMapper {
    AdUser selectByName(String name);
}

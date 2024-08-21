package com.heima.admin.dao;

import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelMapper {
    public List<AdChannel> listAll(String name ,int offset, int size); ;
}

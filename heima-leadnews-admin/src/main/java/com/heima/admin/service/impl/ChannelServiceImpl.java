package com.heima.admin.service.impl;

import com.heima.admin.dao.ChannelMapper;
import com.heima.admin.service.ChannelService;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public List<AdChannel> listByPage(ChannelDto dto) {
        int size = dto.getSize() , page = dto.getPage();
        String name = dto.getName();

        int offset = size * (page-1) ;
        return channelMapper.listAll(name ,offset ,size) ;
    }
}

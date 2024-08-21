package com.heima.admin.service;

import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;

import java.util.List;

public interface ChannelService {

    public List<AdChannel> listByPage(ChannelDto channelDto);
}

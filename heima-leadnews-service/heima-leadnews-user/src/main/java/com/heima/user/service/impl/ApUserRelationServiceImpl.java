package com.heima.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.heima.common.constants.FollowBehaviorConstants;
import com.heima.model.behavior.dtos.FollowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.UserRelationDTO;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.service.ApUserRelationService;
import com.heima.utils.thread.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ApUserRelationServiceImpl implements ApUserRelationService {

    @Autowired
    private KafkaTemplate kakfaTemplate;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 用户关注/取消关注
     * @param dto
     * @return
     */
    @Override
    public ResponseResult follow(UserRelationDTO dto) {
//        1. 校验参数    authorApUserId   必须登录   operation 0  1
        ApUser user = AppThreadLocalUtils.getUser();
        if (dto.getAuthorId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        int operation = dto.getOperation().intValue();
        if (operation != 0 && operation != 1) {
            return  ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "关注类型错误 类型必须为: 0关注 1取关");
        }

        // 记录关注行为
        FollowBehaviorDto followDto = new FollowBehaviorDto();
        followDto.setFollowId(dto.getAuthorId());
        followDto.setArticleId(dto.getArticleId());
        followDto.setUserId(user.getId().intValue());
//异步发送消息，保存关注行为
        kafkaTemplate.send(FollowBehaviorConstants.FOLLOW_BEHAVIOR_TOPIC, JSON.toJSONString(dto));

        return ResponseResult.okResult(200,"关注成功qaq");
    }

}
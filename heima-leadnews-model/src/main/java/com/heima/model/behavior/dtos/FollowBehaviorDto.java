package com.heima.model.behavior.dtos;

import lombok.Data;

@Data
public class FollowBehaviorDto {
    // 设备ID

    Integer equipmentId;
    // 文章ID
    Integer articleId;
    Integer followId;
}

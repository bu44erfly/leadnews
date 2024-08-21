package com.heima.model.behavior.dtos;

import lombok.Data;

@Data
public class ShareBehaviorDto {
    // 设备ID
    Integer equipmentId;
    // 文章ID

    Integer articleId;

    /**
     * 分享渠道
     * 0 微信
     * 1 微信朋友圈
     * 2 QQ
     * 3 QQ 空间
     * 4 微博
     */
    Short type;

}

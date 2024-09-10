package com.heima.api.user;

import com.heima.model.user.pojos.ApUser;

public interface ApUserControllerApi {
    /**
     * 根据id查询app端用户信息
     * @param id
     * @return
     */
    ApUser findUserById(Long id);
}

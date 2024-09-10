package com.heima.search.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dtos.UserSearchDto;

public interface ApAssociateWordsService {
    ResponseResult searchV1(UserSearchDto userSearchDto);
    ResponseResult searchV2(UserSearchDto userSearchDto);


}

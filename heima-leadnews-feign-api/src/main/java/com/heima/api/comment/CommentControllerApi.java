package com.heima.api.comment;

import com.heima.model.comment.CommentSaveDto;
import com.heima.model.common.dtos.ResponseResult;

public interface CommentControllerApi {

    /**
     * 保存评论
     * @param dto
     * @return
     */
    public ResponseResult saveComment(CommentSaveDto dto);
}

package com.heima.comment.service;

import com.heima.model.comment.CommentSaveDto;
import com.heima.model.common.dtos.ResponseResult;

public interface CommentService {

    /**
     * 保存评论
     * @return
     */
     ResponseResult saveComment(CommentSaveDto dto);

}

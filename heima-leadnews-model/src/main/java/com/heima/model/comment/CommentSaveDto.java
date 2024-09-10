package com.heima.model.comment;
import lombok.Data;

@Data
public class CommentSaveDto {

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 评论内容
     */
    private String content;
}
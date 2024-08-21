package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.pojos.ApArticleContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ApArticleContentMapper extends BaseMapper<ApArticleContent> {

    @Update("UPDATE ap_article_content SET content = #{content} WHERE id = #{id}")
    void updateContent(@Param("id") Long id, @Param("content") String content);
}

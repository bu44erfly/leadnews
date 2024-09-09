package com.heima.search.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dtos.UserSearchDto ;

import java.io.IOException;

public interface ArticleSearchService {
    /**
     ES文章分页搜索
     @return
     */
    ResponseResult search(UserSearchDto userSearchDto) throws IOException;

    /**
     * 添加索引文章
     * @param article
     */
   // void saveArticle(SearchArticleVO article);

    /**
     * 删除索引文章
     * @param articleId
     */
   // void deleteArticle(String articleId);
}
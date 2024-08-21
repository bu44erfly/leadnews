package com.heima.behavior.dao;

import com.heima.model.behavior.pojos.ApLikesBehavior;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApLikesBehaviorMapper {
    ApLikesBehavior selectLastLike(@Param("burst") String burst, @Param("objectId") Integer objectId, @Param("entryId") Integer entryId, @Param("type") Short type);
    int insert(ApLikesBehavior apLikesBehavior);
}



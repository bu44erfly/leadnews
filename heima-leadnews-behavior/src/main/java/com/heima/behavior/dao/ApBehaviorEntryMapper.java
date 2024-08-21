package com.heima.behavior.dao;

import com.heima.model.behavior.pojos.ApBehaviorEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApBehaviorEntryMapper {
    ApBehaviorEntry selectByUserIdOrEquipmentId(
             @Param("userId") Long userId, @Param("equipmentId") Integer equipmentId
    );
}

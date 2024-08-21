package com.heima.behavior.dao;

import com.heima.model.behavior.pojos.ApReadBehavior;

public interface ApReadBehaviorMapper {
    int insert(ApReadBehavior record);
    int update(ApReadBehavior record);
    ApReadBehavior selectByEntryId(String burst, Integer entryId, Integer articleId);
}

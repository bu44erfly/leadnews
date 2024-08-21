package com.heima.model.behavior.pojos;

import lombok.Data;

import java.util.Date;

@Data
public class ApForwardBehavior {
    private Long id;

    private Integer entryId;
    private Integer articleId;
    private Integer dynamicId;
    private Date createdTime;
}
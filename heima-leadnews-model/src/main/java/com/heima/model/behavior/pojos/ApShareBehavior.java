package com.heima.model.behavior.pojos;

import lombok.Data;

import java.util.Date;

@Data
public class ApShareBehavior {
    private Long id;

    private Integer entryId;

    private Integer articleId;
    private Short type;
    private Date createdTime;
}
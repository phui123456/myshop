package com.phui.my.shop.domain;

import com.phui.my.shop.commons.lasting.BaseEntity;
import lombok.Data;

@Data
public class TbContendCategory extends BaseEntity {
    private Long parentId;
    private String name ;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;
}

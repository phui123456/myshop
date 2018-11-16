package com.phui.my.shop.commons.lasting;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public abstract class BaseEntity implements Serializable {
    private long id;
    private Date updated;
    private Date created;
}

package com.phui.my.shop.commons.dto;

import com.phui.my.shop.commons.lasting.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PageInfo<T extends BaseEntity> implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;
}

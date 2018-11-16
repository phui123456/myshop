package com.phui.my.shop.web.admin.dao;

import com.phui.my.shop.domain.TbContendCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContendCategoryDao {

    List<TbContendCategory> selectAll();
}

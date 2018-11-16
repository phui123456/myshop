package com.phui.my.shop.web.admin.service.impl;

import com.phui.my.shop.domain.TbContendCategory;
import com.phui.my.shop.web.admin.dao.ContendCategoryDao;
import com.phui.my.shop.web.admin.service.ContendCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContendCategoryServiceImpl implements ContendCategoryService {

    @Autowired
    private ContendCategoryDao contendCategoryDao;

    @Override
    public List<TbContendCategory> selectAll() {
        return contendCategoryDao.selectAll();
    }
}

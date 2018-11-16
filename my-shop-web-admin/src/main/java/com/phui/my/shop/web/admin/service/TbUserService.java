package com.phui.my.shop.web.admin.service;


import com.phui.my.shop.commons.dto.BaseResult;
import com.phui.my.shop.commons.dto.PageInfo;
import com.phui.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {

    TbUser login(String email, String password);
    //查询所有
    List<TbUser> selectAll();

    BaseResult save(TbUser tbUser);

    void update(TbUser tbUser);

    TbUser getById(Long id);

    List<TbUser> search(TbUser tbUser);
    //分页查询
    PageInfo<TbUser> page(int start, int length,int draw);
    //查询总条数
    int count();
    //批量删除
    void deleteMail(String[] ids);
}

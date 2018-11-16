package com.phui.my.shop.web.admin.dao;


import com.phui.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao {

    TbUser getByEmail(String email);

    List<TbUser> selectAll();

    void insect(TbUser tbUser);

    void update(TbUser tbUser);

    TbUser getById(Long id);

    List<TbUser> search(TbUser tbUser);

    List<TbUser> page(Map<String,Object> params);

    int  count();
    //批量删除
    void deleteMail(String[] ids);
}

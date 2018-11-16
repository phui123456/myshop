package com.phui.my.shop.web.admin.service.impl;


import com.phui.my.shop.commons.dto.BaseResult;
import com.phui.my.shop.commons.dto.PageInfo;
import com.phui.my.shop.commons.utils.RegexpUtils;
import com.phui.my.shop.domain.TbUser;
import com.phui.my.shop.web.admin.dao.TbUserDao;
import com.phui.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(String email, String password) {
        TbUser user = tbUserDao.getByEmail(email);
        if(user!=null){
            String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
            if(md5DigestAsHex.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkUser(tbUser);
        if (baseResult.getStatus() == BaseResult.STRING_STATUS) {
            tbUser.setUpdated(new Date());
            //新增
            if (tbUser.getId() == 0) {
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserDao.insect(tbUser);
            }
            //修改
            else {
                tbUserDao.update(tbUser);
            }
                baseResult.setMassage("保存用户成功");
            }

            return baseResult;
        }


    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public List<TbUser> search(TbUser tbUser) {
        return tbUserDao.search(tbUser);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length,int draw) {
        int count = tbUserDao.count();

        HashMap<String, Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);

        PageInfo<TbUser> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        pageInfo.setData(tbUserDao.page(params));
        return pageInfo;
    }

    @Override
    public int count() {
        return tbUserDao.count();
    }

    @Override
    public void deleteMail(String[] ids) {
        tbUserDao.deleteMail(ids);
    }


    private BaseResult checkUser(TbUser tbUser){
        BaseResult baseResult = BaseResult.success();
        if(StringUtils.isBlank(tbUser.getUsername())){
            baseResult=BaseResult.fail("用户名不能为空");
        }
        else if(StringUtils.isBlank(tbUser.getPassword())){
            baseResult=BaseResult.fail("密码不能为空");
        }
        else if(StringUtils.isBlank(tbUser.getEmail())){
            baseResult=BaseResult.fail("手机号不能为空");
        }
        else if(!RegexpUtils.checkEmail(tbUser.getEmail())){
            baseResult=BaseResult.fail("手机号格式不正确");
        }
        else if(StringUtils.isBlank(tbUser.getPhone())){
            baseResult=BaseResult.fail("邮箱不能为空");
        }
        else if(!RegexpUtils.checkPhone(tbUser.getPhone())){
            baseResult=BaseResult.fail("邮箱格式不正确");
        }

        return baseResult;
    }
}

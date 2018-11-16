package com.phui.my.shop.web.admin.web.controller;

import com.phui.my.shop.commons.commonutils.Constant;
import com.phui.my.shop.commons.commonutils.CookieUtils;
import com.phui.my.shop.domain.TbUser;
import com.phui.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(TbUser tbUser, HttpServletRequest request, Model model){
        //用户名和密码的展示
        isRemember(tbUser,request,model);

        TbUser admin = (TbUser) request.getSession().getAttribute(Constant.COOKIE_USER);
        if(admin!=null){
            return "redirect:/main";
        }
        return "login";
    }

    private void isRemember(TbUser tbUser, HttpServletRequest request, Model model) {
        String cookieValue = CookieUtils.getCookieValue(request, Constant.COOKIE_USER);
        if (StringUtils.isNotBlank(cookieValue)) {
            String[] cookieValues = cookieValue.split(":");
            tbUser.setEmail(cookieValues[0]);
            tbUser.setPassword(cookieValues[1]);
            tbUser.setRemember("on");
            model.addAttribute(Constant.COOKIE_USER, tbUser);
        }
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login( TbUser tbUser,String email, String password, HttpServletRequest request, HttpServletResponse response){
        TbUser admin = tbUserService.login(tbUser.getEmail(),tbUser.getPassword());

        // 不需要记住我
        if (!"on".equals(tbUser.getRemember())) {
            CookieUtils.deleteCookie(request, response,Constant.COOKIE_USER);
        }

        //登录失败
        if(admin==null){
            request.getSession().setAttribute("message","用户名或密码错误");
            return "redirect:/login";
        }
        //登录成功
        else{
            if("on".equals(tbUser.getRemember())){

                CookieUtils.setCookie(request, response, Constant.COOKIE_USER, String.format("%s:%s", tbUser.getEmail(),tbUser.getPassword()), 7 * 24 * 60 * 60);

            }
            request.getSession().setAttribute(Constant.COOKIE_USER,admin);
            return "redirect:/main";
        }


    }

}

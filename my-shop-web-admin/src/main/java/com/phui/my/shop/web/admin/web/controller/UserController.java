package com.phui.my.shop.web.admin.web.controller;

import com.phui.my.shop.commons.dto.BaseResult;
import com.phui.my.shop.commons.dto.PageInfo;
import com.phui.my.shop.domain.TbUser;
import com.phui.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUser",tbUsers);
        return "user_list";
    }

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser=null;

        if(id!=null){
            tbUserService.getById(id);
        }
        else{
            tbUser=new TbUser();
        }
        return tbUser;
    }

    @RequestMapping(value = "from",method = RequestMethod.GET)
    public String from(){

        return "user_from";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String from(TbUser tbUser, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbUserService.save(tbUser);

        //保存成功
        if(baseResult.getStatus()==BaseResult.STRING_STATUS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else{
            model.addAttribute("baseResult",baseResult);
            return "user_from";
        }
    }
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(TbUser tbUser,Model model){
        List<TbUser> tbUsers = tbUserService.search(tbUser);
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult=null;
        if(StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            tbUserService.deleteMail(idArray);
            baseResult=BaseResult.success("删除用户成功");
        }
        else{
            baseResult= BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null?0:Integer.parseInt(strDraw);
        int start = strStart == null?0:Integer.parseInt(strStart);
        int length = strLength == null?10:Integer.parseInt(strLength);

        PageInfo<TbUser> page = tbUserService.page(draw, start, length);
        return page;
    }
}

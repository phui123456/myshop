package com.phui.my.shop.web.admin.web.controller;

import com.phui.my.shop.domain.TbContendCategory;
import com.phui.my.shop.web.admin.service.ContendCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "category")
public class ContendCategory {

    @Autowired
    private ContendCategoryService contendCategoryService;
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbContendCategory> targetList = new ArrayList<>();
        List<TbContendCategory> sourceList = contendCategoryService.selectAll();
        
        sourceList(sourceList,targetList,0L);
       
        model.addAttribute("contendCategories",targetList);
        return "category_list";
    }

    private void sourceList(List<TbContendCategory> sourceList, List<TbContendCategory> targetList, long parentId) {
        for (TbContendCategory tbContendCategory : sourceList) {
            if(tbContendCategory.getParentId().equals(parentId)){
                targetList.add(tbContendCategory);

                if(tbContendCategory.getIsParent()){
                    for (TbContendCategory contendCategory : sourceList) {
                        if(contendCategory.getParentId().equals(tbContendCategory.getId())){
                            sourceList(sourceList,targetList,tbContendCategory.getId());
                            break;
                        }
                    }
                }
            }
        }

   }
}

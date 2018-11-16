package com.phui.my.shop.commons.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseResult implements Serializable {

    public static final int STRING_STATUS=200;
    public static final int STRING_FAIL=500;
    private int status;
    private String massage;

    public static BaseResult success(){
        return BaseResult.createResult(STRING_STATUS,"成功");
    }

    public static BaseResult success(String massage){
        return BaseResult.createResult(STRING_STATUS,massage);
    }

    public static BaseResult fail(){
        return BaseResult.createResult(STRING_FAIL,"失败");
    }

    public static BaseResult fail(String massage){
        return BaseResult.createResult(STRING_FAIL,massage);
    }

    public static BaseResult fail(int status,String massage){
        return BaseResult.createResult(status, massage);
    }


    public static BaseResult createResult(int status,String massage){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMassage(massage);
        return baseResult;
    }
}

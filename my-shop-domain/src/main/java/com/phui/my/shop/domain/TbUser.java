package com.phui.my.shop.domain;

import com.phui.my.shop.commons.lasting.BaseEntity;
import lombok.Data;

@Data
public class TbUser extends BaseEntity {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String remember;

}

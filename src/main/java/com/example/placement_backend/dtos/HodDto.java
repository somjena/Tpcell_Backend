package com.example.placement_backend.dtos;

import com.example.placement_backend.entity.HOD;

public class HodDto extends HOD {

    private String pwd;

    public String getPwd() {
        return pwd;
    }


    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public static HOD toEntity(HodDto dto) {
        HOD entity=new HOD();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}

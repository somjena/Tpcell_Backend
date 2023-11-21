package com.example.placement_backend.dtos;

public class CompanyDTO extends {


    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "CompanyDTO [pwd=" + pwd + "]";
    }

    public static Company toEntity(CompanyDTO dto) {
        Company entity=new Company();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}

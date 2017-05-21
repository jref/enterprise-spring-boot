package com.ua.codespace.rest.dto;


public class CompanyDto {
    private String name;

    public CompanyDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

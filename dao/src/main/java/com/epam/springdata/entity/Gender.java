package com.epam.springdata.entity;

public enum Gender {
    MAN("123"), WOMAN("456");

    private String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

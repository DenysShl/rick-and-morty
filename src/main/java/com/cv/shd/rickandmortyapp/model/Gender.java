package com.cv.shd.rickandmortyapp.model;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("Unknow");

    private String value;

    Gender(String value) {
        this.value = value;
    }
}

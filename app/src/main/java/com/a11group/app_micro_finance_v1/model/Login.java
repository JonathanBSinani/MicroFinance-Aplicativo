package com.a11group.app_micro_finance_v1.model;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("id")
    private Long id;

    public Login(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

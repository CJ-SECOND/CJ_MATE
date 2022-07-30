package com.frontend.cj_app.common.payload;

import com.google.gson.annotations.SerializedName;

public class Coury_Response {

    @SerializedName("total_cnt")
    private int total_cnt;
    @SerializedName("wrong_cnt")
    private int wrong_cnt;
    @SerializedName("complete_cnt")
    private int complete_cnt;
    @SerializedName("damage_cnt")
    private int damage_cnt;
    @SerializedName("REPL_CD")
    private String REPL_CD;
    @SerializedName("REPL_MSG")
    private String REPL_MSG;


    public int getTotal_cnt() {
        return total_cnt;
    }

    public void setTotal_cnt(int total_cnt) {
        this.total_cnt = total_cnt;
    }

    public int getWrong_cnt() {
        return wrong_cnt;
    }

    public void setWrong_cnt(int wrong_cnt) {
        this.wrong_cnt = wrong_cnt;
    }

    public int getComplete_cnt() {
        return complete_cnt;
    }

    public void setComplete_cnt(int complete_cnt) {
        this.complete_cnt = complete_cnt;
    }

    public int getDamage_cnt() {
        return damage_cnt;
    }

    public void setDamage_cnt(int damage_cnt) {
        this.damage_cnt = damage_cnt;
    }

    public String getREPL_CD() {
        return REPL_CD;
    }

    public void setREPL_CD(String REPL_CD) {
        this.REPL_CD = REPL_CD;
    }

    public String getREPL_MSG() {
        return REPL_MSG;
    }

    public void setREPL_MSG(String REPL_MSG) {
        this.REPL_MSG = REPL_MSG;
    }
}
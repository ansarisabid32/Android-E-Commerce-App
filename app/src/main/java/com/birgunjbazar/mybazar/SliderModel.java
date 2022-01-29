package com.birgunjbazar.mybazar;

public class SliderModel {
    private int banner;
    private String backgrounColor;

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public String getBackgrounColor() {
        return backgrounColor;
    }

    public void setBackgrounColor(String backgrounColor) {
        this.backgrounColor = backgrounColor;
    }

    public SliderModel(int banner, String backgrounColor) {
        this.banner = banner;
        this.backgrounColor = backgrounColor;
    }
}


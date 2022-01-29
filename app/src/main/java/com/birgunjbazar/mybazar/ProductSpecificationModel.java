package com.birgunjbazar.mybazar;

public class ProductSpecificationModel {
    public static final int SPECIFICATION_TITLE=0,SPECIFICATION_BODY=1;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    ////specification title
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProductSpecificationModel(String title) {
        this.title = title;
    }

    public ProductSpecificationModel(int type, String title) {
        this.type = type;
        this.title = title;
    }
    ////specification title

    ////specification body
    private String feature_name,feature_value;
    public ProductSpecificationModel(int type, String feature_name, String feature_value) {
        this.type = type;
        this.feature_name = feature_name;
        this.feature_value = feature_value;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }

    public String getFeature_value() {
        return feature_value;
    }

    public void setFeature_value(String feature_value) {
        this.feature_value = feature_value;
    }
    ////specification body

}

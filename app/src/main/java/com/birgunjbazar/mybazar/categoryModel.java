package com.birgunjbazar.mybazar;

public class categoryModel {

    private String categoryIconLink;
    private String categoryName;
    private String category_id;

    public categoryModel(String categoryIconLink, String categoryName, String category_id) {
        this.categoryIconLink = categoryIconLink;
        this.categoryName = categoryName;
        this.category_id = category_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategoryIconLink() {
        return categoryIconLink;
    }

    public void setCategoryIconLink(String categoryIconLink) {
        this.categoryIconLink = categoryIconLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

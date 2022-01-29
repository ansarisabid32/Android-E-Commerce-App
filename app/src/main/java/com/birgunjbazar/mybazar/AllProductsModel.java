package com.birgunjbazar.mybazar;

public class AllProductsModel {
    private String id;
    private String name;
    private String status;
    private String featured;
    private String description;
    private String short_description;
    private String regular_price;
    private String sale_price;
    private String tax_status;
    private String stock_status;
    private String shipping_taxable;
    private String rating_count;
    private String average_rating;
    private String category_name;
    private String category_id;
    private String image_src;

    public AllProductsModel(String id, String name, String status, String featured, String description, String short_description, String regular_price, String sale_price, String tax_status, String stock_status, String shipping_taxable, String rating_count, String average_rating, String category_name, String category_id, String image_src) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.featured = featured;
        this.description = description;
        this.short_description = short_description;
        this.regular_price = regular_price;
        this.sale_price = sale_price;
        this.tax_status = tax_status;
        this.stock_status = stock_status;
        this.shipping_taxable = shipping_taxable;
        this.rating_count = rating_count;
        this.average_rating = average_rating;
        this.category_name = category_name;
        this.category_id = category_id;
        this.image_src = image_src;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getTax_status() {
        return tax_status;
    }

    public void setTax_status(String tax_status) {
        this.tax_status = tax_status;
    }

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    public String getShipping_taxable() {
        return shipping_taxable;
    }

    public void setShipping_taxable(String shipping_taxable) {
        this.shipping_taxable = shipping_taxable;
    }

    public String getRating_count() {
        return rating_count;
    }

    public void setRating_count(String rating_count) {
        this.rating_count = rating_count;
    }

    public String getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(String average_rating) {
        this.average_rating = average_rating;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }
}

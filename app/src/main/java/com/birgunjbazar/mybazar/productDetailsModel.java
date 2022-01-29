package com.birgunjbazar.mybazar;

import java.util.List;

public class productDetailsModel {
    private String id;
    private String name;
    private String status;
    private String featured;
    private String description;
    private String short_description;
    private String price;
    private String regular_price;
    private String on_sale;
    private String stock_status;
    private String average_rating;
    private String rating_count;
    private List<String> categories;
    private List<String> images;

    public productDetailsModel(String id, String name, String status, String featured, String description, String short_description, String price, String regular_price, String on_sale, String stock_status, String average_rating, String rating_count, List<String> categories, List<String> images) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.featured = featured;
        this.description = description;
        this.short_description = short_description;
        this.price = price;
        this.regular_price = regular_price;
        this.on_sale = on_sale;
        this.stock_status = stock_status;
        this.average_rating = average_rating;
        this.rating_count = rating_count;
        this.categories = categories;
        this.images = images;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getOn_sale() {
        return on_sale;
    }

    public void setOn_sale(String on_sale) {
        this.on_sale = on_sale;
    }

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    public String getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(String average_rating) {
        this.average_rating = average_rating;
    }

    public String getRating_count() {
        return rating_count;
    }

    public void setRating_count(String rating_count) {
        this.rating_count = rating_count;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

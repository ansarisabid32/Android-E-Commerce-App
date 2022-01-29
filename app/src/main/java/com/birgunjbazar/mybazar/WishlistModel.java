package com.birgunjbazar.mybazar;

public class WishlistModel {
    private String productImage;
    private String productTitle;
    private String freeCoupons;
    private String rating;
    private String totalRatings;
    private String productPrice;
    private String cuttedProductPrice;
    private boolean COD;

    public WishlistModel(String productImage, String productTitle, String freeCoupons, String rating, String totalRatings, String productPrice, String cuttedProductPrice, boolean COD) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCoupons = freeCoupons;
        this.rating = rating;
        this.totalRatings = totalRatings;
        this.productPrice = productPrice;
        this.cuttedProductPrice = cuttedProductPrice;
        this.COD = COD;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getFreeCoupons() {
        return freeCoupons;
    }

    public void setFreeCoupons(String freeCoupons) {
        this.freeCoupons = freeCoupons;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(String totalRatings) {
        this.totalRatings = totalRatings;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedProductPrice() {
        return cuttedProductPrice;
    }

    public void setCuttedProductPrice(String cuttedProductPrice) {
        this.cuttedProductPrice = cuttedProductPrice;
    }

    public boolean isCOD() {
        return COD;
    }

    public void setCOD(boolean COD) {
        this.COD = COD;
    }
}

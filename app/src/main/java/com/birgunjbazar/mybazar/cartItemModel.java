package com.birgunjbazar.mybazar;

public class cartItemModel {
    public static final int CART_ITEM=0, TOTAL_AMOUNT=1;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /// Cart Item
    private int productImage,freeCoupon,productQuantity,offersApplied,couponsApplied;
    private String productTitle,productPrice,productCuttedPrice;

    public cartItemModel(int type, int productImage, String productTitle, int freeCoupon, String productPrice, String productCuttedPrice, int productQuantity, int offersApplied, int couponsApplied) {
        this.type = type;
        this.productImage = productImage;
        this.freeCoupon = freeCoupon;
        this.productQuantity = productQuantity;
        this.offersApplied = offersApplied;
        this.couponsApplied = couponsApplied;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productCuttedPrice = productCuttedPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getFreeCoupon() {
        return freeCoupon;
    }

    public void setFreeCoupon(int freeCoupon) {
        this.freeCoupon = freeCoupon;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getOffersApplied() {
        return offersApplied;
    }

    public void setOffersApplied(int offersApplied) {
        this.offersApplied = offersApplied;
    }

    public int getCouponsApplied() {
        return couponsApplied;
    }

    public void setCouponsApplied(int couponsApplied) {
        this.couponsApplied = couponsApplied;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCuttedPrice() {
        return productCuttedPrice;
    }

    public void setProductCuttedPrice(String productCuttedPrice) {
        this.productCuttedPrice = productCuttedPrice;
    }
    /// Cart Item

    /// cart total
    private int totalItem;
    private String totalAmount;
    private String deliveryPrice;
    private String savedAmount;
    private String totalItemPrice;

    public cartItemModel(int type, int totalItem, String totalItemPrice, String deliveryPrice, String totalAmount, String savedAmount) {
        this.type = type;
        this.totalItem = totalItem;
        this.totalAmount = totalAmount;
        this.deliveryPrice = deliveryPrice;
        this.savedAmount = savedAmount;
        this.totalItemPrice = totalItemPrice;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }

    public String getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(String totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
/// cart total
}

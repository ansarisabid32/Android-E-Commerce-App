package com.birgunjbazar.mybazar;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomePageModel {
    private int type;
    private String backgroundColor;
    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER=1;
    public static final int HORIZONTAL_PRODUCT_VIEW=2;
    public static final int GRID_PRODUCT_LAYOUT=3;


    /////Banner Slider
    private List<SliderModel> sliderModelList;
    public HomePageModel(List<SliderModel> sliderModelList, int type) {
        this.sliderModelList = sliderModelList;
        this.type = type;
    }
    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }
    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    /////

    ////Strip AD BANNER
    private int resource;

    public HomePageModel(int type, int resource, String backgroundColor) {
        this.type = type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }
    public int getResource() {
        return resource;
    }
    public void setResource(int resource) {
        this.resource = resource;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    ///

    ///Horizontal Recycler View
    private String title;
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    private List<WishlistModel> viewAllProductList;

    public HomePageModel(int type, String title,String backgroundColor, List<HorizontalProductScrollModel> horizontalProductScrollModelList, List<WishlistModel> viewAllProductList) {
        this.type = type;
        this.title = title;
        this.backgroundColor=backgroundColor;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
        this.viewAllProductList=viewAllProductList;
    }

    public List<WishlistModel> getViewAllProductList() {
        return viewAllProductList;
    }

    public void setViewAllProductList(List<WishlistModel> viewAllProductList) {
        this.viewAllProductList = viewAllProductList;
    }

    public HomePageModel(int type, String title, String backgroundColor, List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor=backgroundColor;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelList() {
        return horizontalProductScrollModelList;
    }
    public void setHorizontalProductScrollModelList(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }
    ////

    /// GRID PRODUCT LAYOUT
    ////same as Horizontal Recycler View
    ////

    /// ViewAll Activity
    ///
}

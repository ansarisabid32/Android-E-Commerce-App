package com.birgunjbazar.mybazar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ViewPager productImagesViewPager,productDescriptionViewPager;
    private TabLayout viewPagerIndicator,productDescriptionTabLayout;
    private Button couponRedeemButton;
    private TextView productTitle;
    private static boolean ALREADY_ADDED_TO_WISHLIST=false;
    private FloatingActionButton addToWishlistButton;

    /////coupon dialog
    public static TextView couponTitle,couponBody,couponExpiryDate;
    private static RecyclerView couponsRV;
    private static LinearLayout selectedCoupon;
    ////coupon dialog

    //////rating Layout
    private LinearLayout rateNowLayout;
    //////rating Layout

    private Button buyNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Product details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager=(ViewPager)findViewById(R.id.productImagesLayout_viewPager);
        viewPagerIndicator=(TabLayout)findViewById(R.id.tabLayout);
        addToWishlistButton=(FloatingActionButton)findViewById(R.id.productImagesLayout_floatingActionButton);

        productDescriptionViewPager=(ViewPager)findViewById(R.id.productDescriptionLayout_viewPager);
        productDescriptionTabLayout=(TabLayout)findViewById(R.id.productDescriptionLayout_tabLayout);

        buyNowButton=findViewById(R.id.buy_now_button);

        couponRedeemButton=findViewById(R.id.coupon_redemption_button);

        productTitle=findViewById(R.id.productImageLayout_productTitle);

        productTitle.setText("jfvjf");

        List<String> productImages =new ArrayList<>();
//        productImages.add(R.drawable.sample);
//        productImages.add(R.drawable.sample);
//        productImages.add(R.drawable.sample);
//        productImages.add(R.drawable.sample);

        ProductImagesAdapter productImagesAdapter=new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewPagerIndicator.setupWithViewPager(productImagesViewPager,true);

        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ALREADY_ADDED_TO_WISHLIST){
                    ALREADY_ADDED_TO_WISHLIST=false;
                    addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                }
                else{
                    ALREADY_ADDED_TO_WISHLIST=true;
                    addToWishlistButton.setSupportImageTintList(getResources().getColorStateList(R.color.red));
                }
            }
        });

        productDescriptionViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDescriptionTabLayout.getTabCount()));
        productDescriptionViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDescriptionTabLayout));
        productDescriptionTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDescriptionViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ///////rating layout
        rateNowLayout=(LinearLayout)findViewById(R.id.rating_rateNowContainer);
        for (int x=0;x<rateNowLayout.getChildCount();x++){
            final int starPosition=x;
            rateNowLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }
         
        ///////rating layout

        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent=new Intent(ProductDetailsActivity.this,DeliveryActivity.class);
                startActivity(deliveryIntent);
            }
        });

        /////coupon dialog

        Dialog couponRedeenDialog=new Dialog(ProductDetailsActivity.this);
        couponRedeenDialog.setContentView(R.layout.coupon_redeem_dialog);
        couponRedeenDialog.setCancelable(true);
        couponRedeenDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView openToggleRV=couponRedeenDialog.findViewById(R.id.couponRedeemDialog_toggleRecyclerView);
        couponsRV=couponRedeenDialog.findViewById(R.id.couponRedeemDialog_couponsRecyclerView);
        selectedCoupon=couponRedeenDialog.findViewById(R.id.couponRedeemDialog_selectedCoupon);

        couponTitle=couponRedeenDialog.findViewById(R.id.rewardsItem_couponTitle);
        couponExpiryDate=couponRedeenDialog.findViewById(R.id.rewardsItem_couponValidity);
        couponBody=couponRedeenDialog.findViewById(R.id.rewardsItem_couponBody);

        TextView orignalPrice=couponRedeenDialog.findViewById(R.id.couponRedeemDialog_orignalPrice);
        TextView discountedPrice=couponRedeenDialog.findViewById(R.id.couponRedeemDialog_discountedPrice);

        LinearLayoutManager layoutManager=new LinearLayoutManager(ProductDetailsActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        couponsRV.setLayoutManager(layoutManager);

        List<RewardsModel> rewardsModelList=new ArrayList<>();
        rewardsModelList.add(new RewardsModel("Cashback","till 10th Feb 2021","GET 20% CASHBACK on any product above Rs. 200/- and below Rs. 1000/-."));
        rewardsModelList.add(new RewardsModel("DISCOUNT","till 5th Feb 2021","GET 10% DISCOUNT on any product above Rs. 2000/- and below Rs. 3000/-."));
        rewardsModelList.add(new RewardsModel("Buy 1 Get 1 Free","till 9th Feb 2021","GET 15% CASHBACK on any product above Rs. 5000/- and below Rs. 10000/-."));
        rewardsModelList.add(new RewardsModel("Cashback","till 20th Feb 2021","GET 20% CASHBACK on any product above Rs. 500/- and below Rs.5000/-."));
        MyRewardsAdapter myRewardsAdapter=new MyRewardsAdapter(rewardsModelList,true);
        couponsRV.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        openToggleRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        showDialogRecyclerView();
                    }
        });
        couponRedeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                couponRedeenDialog.show();

            }
        });
    }
    public static void showDialogRecyclerView(){
        if(couponsRV.getVisibility()==View.GONE){
            couponsRV.setVisibility(View.VISIBLE);
            selectedCoupon.setVisibility(View.GONE);
        }else{
            couponsRV.setVisibility(View.GONE);
            selectedCoupon.setVisibility(View.VISIBLE);
        }
    }

    /////coupon Dialog

    private void setRating(int starPosition) {
        for (int x=0;x<rateNowLayout.getChildCount();x++){
            ImageView starBtn =(ImageView)rateNowLayout.getChildAt(x);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            }
            if(x<=starPosition){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_search)
        {
            //search icon code
            return true;
        }
        else if(id==R.id.action_cart)
        {
            //cart icon code
            Intent cartIntent=new Intent(ProductDetailsActivity.this,MainActivity.class);
            MainActivity.SHOW_CART=true;
            startActivity(cartIntent);
            return true;
        }
        else if(id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.birgunjbazar.mybazar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.birgunjbazar.mybazar.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static int HOME_FRAGMENT=0;
    private static int CART_FRAGMENT=1;
    private static int ORDERS_FRAGMENT=2;
    private static int WISHLIST_FRAGMENT=3;
    private static int REWARDS_FRAGMENT=4;
    private static int MYACCOUNT_FRAGMENT=5;
    private ImageView noInternetConnection;

    public static boolean SHOW_CART=false;
    private AppBarConfiguration mAppBarConfiguration;
    private ConstraintLayout frameLayout;
    private int CURRENT_FRAGMENT=-1;
    private NavigationView navigationView;
    private NavController navController;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawer;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private ImageView actionBar_logo;
    private Toolbar toolbar;
    private boolean doubleBackToExitPressedOnce = false;

    private Window window;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        actionBar_logo=findViewById(R.id.action_bar_logo);
        setSupportActionBar(toolbar);

        SystemClock.sleep(1000);

        window=getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        /*Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent); finish();*/

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        noInternetConnection=findViewById(R.id.noInternetConnection);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/

//        fragmentManager=getSupportFragmentManager();
//        fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.nav_host_fragment,new HomeFragment());
//        fragmentTransaction.commit();

        frameLayout=(ConstraintLayout)findViewById(R.id.main_frame_layout);
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()==true) {
            noInternetConnection.setVisibility(View.GONE);
            if (SHOW_CART) {
                drawer.setDrawerLockMode(1);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                gotoFragment("My Cart", new MyCartFragment(), -2);

            } else {
                actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
                drawer.addDrawerListener(actionBarDrawerToggle);
                actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
                actionBarDrawerToggle.syncState();
                setFragment(new HomeFragment(), HOME_FRAGMENT);
            }
        }else{
            noInternetConnection.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            if(CURRENT_FRAGMENT==HOME_FRAGMENT) {
                if(doubleBackToExitPressedOnce){
                    CURRENT_FRAGMENT=-1;
                    super.onBackPressed();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce=false;
                    }
                }, 2000);
            }else{
                if(SHOW_CART){
                    SHOW_CART=false;
                    finish();
                }else {
                    actionBar_logo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new HomeFragment(), HOME_FRAGMENT);
                    navigationView.getMenu().getItem(0).setChecked(true);
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(CURRENT_FRAGMENT==HOME_FRAGMENT) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_search)
        {
            //search icon code

            System.out.println(APICall.productDetailsModelList.size());

            return true;
        }
        else if(id==R.id.action_notification)
        {
            //notification icon code
            Toast.makeText(MainActivity.this, "Notification Clicked", Toast.LENGTH_LONG).show();
            Intent intt=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intt);
            return true;
        }
        else if(id==R.id.action_cart)
        {
            //cart icon code
            gotoFragment("My Cart", new MyCartFragment(),CART_FRAGMENT);
            return true;
        }
        else if(id==android.R.id.home){
            if(SHOW_CART){
                SHOW_CART=false;
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void gotoFragment(String title,Fragment fragment,int fragmentNo){
        actionBar_logo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment,fragmentNo);
        if(fragmentNo==CART_FRAGMENT) {
            navigationView.getMenu().getItem(2).setChecked(true);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setFragment(Fragment fragment,int fragmentNo) {
        if(fragmentNo!=CURRENT_FRAGMENT) {
            if(fragmentNo==REWARDS_FRAGMENT){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(Color.parseColor("#5B04B1"));
                    toolbar.setBackgroundColor(Color.parseColor("#5B04B1"));
                }
            }else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
            CURRENT_FRAGMENT = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.nav_mall){
            actionBar_logo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeFragment(),HOME_FRAGMENT);
        }
        else if(id==R.id.nav_orders){
            gotoFragment("My Orders", new MyOrdersFragment(),ORDERS_FRAGMENT);

        }
        else if(id==R.id.nav_cart){
            gotoFragment("My Cart", new MyCartFragment(),CART_FRAGMENT);
        }
        else if(id==R.id.nav_rewards){
            gotoFragment("My Rewards", new MyRewardsFragment(),REWARDS_FRAGMENT);
        }
        else if(id==R.id.nav_wishlist){
            gotoFragment("My Wishlist", new MyWishlistFragment(),WISHLIST_FRAGMENT);

        }
        else if(id==R.id.nav_account){
            gotoFragment("My Account", new MyAccountFragment(),MYACCOUNT_FRAGMENT);

        }
        else if(id==R.id.nav_logout){

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
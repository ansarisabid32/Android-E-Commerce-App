package com.birgunjbazar.mybazar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView category_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        String title=getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        category_recyclerView=(RecyclerView)findViewById(R.id.category_RecycleView);


        /////banner slider
        List<SliderModel> sliderModelList=new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.slider2,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider3,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider4,"#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.slider,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider2,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider3,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider4,"#077AE4"));

        sliderModelList.add(new SliderModel(R.drawable.slider,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider2,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider3,"#077AE4"));

        /////

        ///Horizontal Product layout

        List<HorizontalProductScrollModel> horizontalProductScrollModelList=new ArrayList<HorizontalProductScrollModel>();
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sample,"Redmi 5A","SD 575 Processor","Rs. 5999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sample,"J7 Max","SD 765 Processor","Rs. 9999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sample,"Oppo A3","SD 730g Processor","Rs. 12999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sample,"Iphone 11","SD 855 Processor","Rs. 7999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sample,"Poco c3","SD 675 Processor","Rs. 19999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sample,"Infinx Hot 9 Pro","SD 925 Processor","Rs. 25999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sample,"Oppo A3","SD 730g Processor","Rs. 12999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sample,"Realme 2","SD 660g Processor","Rs. 10599"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.sample,"Samsung J7","SD 425g Processor","Rs. 7999"));



        ///

        ///Grid Product Layout

        ///

        /////Strip Ad

        //// Strip Ad


        /////test recycler view
        LinearLayoutManager testingLayoutManager =new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        category_recyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(sliderModelList,0));
        homePageModelList.add(new HomePageModel(2,"Sabid Ansari","#32e66b",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider2,"#000000"));
        homePageModelList.add(new HomePageModel(sliderModelList,0));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider,"#ffff00"));
        homePageModelList.add(new HomePageModel(3,"Sabid Ansari","#a5f2e4",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(sliderModelList,0));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider4,"#ff0000"));
        homePageModelList.add(new HomePageModel(2,"Sabid Ansari","#b34658",horizontalProductScrollModelList));
        HomePageAdapter adapter =new HomePageAdapter(homePageModelList);
        category_recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
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
        else if(id==android.R.id.home){
            Toast.makeText(this, "yes I clicked", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
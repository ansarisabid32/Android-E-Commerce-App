package com.birgunjbazar.mybazar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridView gridView;
    public static List<WishlistModel> wishlistModelList;
    public static WishlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int layout_code=getIntent().getIntExtra("Layout_code",-1);

        if(layout_code==0) {
            recyclerView = findViewById(R.id.viewAll_recyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            wishlistModelList = new ArrayList<>();
//            wishlistModelList.add(new WishlistModel("", "Samsung J7 Max", 2, "2.5", 14, "Rs. 5999/-", "Rs. 2999/-", true));
//            wishlistModelList.add(new WishlistModel("", "Samsung J7 Prime", 0, "3.5", 24, "Rs. 8999/-", "Rs. 3999/-", true));
//            wishlistModelList.add(new WishlistModel("", "Samsung J3", 4, "4", 9, "Rs. 15999/-", "Rs. 3999/-", true));
//            wishlistModelList.add(new WishlistModel("", "Redmi note 9", 1, "5", 4, "Rs. 12999/-", "Rs. 1999/-", false));
//            wishlistModelList.add(new WishlistModel("", "Oppo A7", 0, "1.5", 22, "Rs. 9999/-", "Rs. 4999/-", false));
//            wishlistModelList.add(new WishlistModel("", "Poco C3", 3, "2", 19, "Rs. 8999/-", "Rs. 1999/-", true));
//            wishlistModelList.add(new WishlistModel("", "Samsung J5", 2, "3.5", 16, "Rs. 7999/-", "Rs. 2999/-", false));

            ViewAllActivity.wishlistModelList=APICall.wishlistModelList;
            //System.out.println("-----------------"+wishlistModelList.size()+"------------------------");
            adapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(layout_code==1) {
            gridView = findViewById(R.id.viewAll_gridView);
            gridView.setVisibility(View.VISIBLE);

            List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<HorizontalProductScrollModel>();
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Redmi 5A", "SD 575 Processor", "Rs. 5999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "J7 Max", "SD 765 Processor", "Rs. 9999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Oppo A3", "SD 730g Processor", "Rs. 12999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Iphone 11", "SD 855 Processor", "Rs. 7999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Poco c3", "SD 675 Processor", "Rs. 19999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Infinx Hot 9 Pro", "SD 925 Processor", "Rs. 25999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Oppo A3", "SD 730g Processor", "Rs. 12999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Realme 2", "SD 660g Processor", "Rs. 10599"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Samsung J7", "SD 425g Processor", "Rs. 7999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "J7 Max", "SD 765 Processor", "Rs. 9999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Oppo A3", "SD 730g Processor", "Rs. 12999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Iphone 11", "SD 855 Processor", "Rs. 7999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Poco c3", "SD 675 Processor", "Rs. 19999"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Infinx Hot 9 Pro", "SD 925 Processor", "Rs. 25999"));

            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductLayoutAdapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
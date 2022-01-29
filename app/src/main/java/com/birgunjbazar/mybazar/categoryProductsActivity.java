package com.birgunjbazar.mybazar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class categoryProductsActivity extends AppCompatActivity {
    private RecyclerView categoryProductRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);
        String title=getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryProductRecyclerView=findViewById(R.id.categoryProducts_recyclerView);

        List<categoryProductsModel> categoryProductsModelList=new ArrayList<>();
        categoryProductsModelList.add(new categoryProductsModel("","abs","0","3","3","25000","27000",true));
        for (int i=0;i<APICall.productDetailsModelList.size()/2;i++){
            for (int j=0;j<APICall.productDetailsModelList.get(i).getCategories().size();j++){
                if(title.equals(APICall.productDetailsModelList.get(i).getCategories().get(j))){
                    categoryProductsModelList.add(new categoryProductsModel(APICall.productDetailsModelList.get(i).getImages().get(0),APICall.productDetailsModelList.get(i).getName(),"0",APICall.productDetailsModelList.get(i).getAverage_rating(),APICall.productDetailsModelList.get(i).getRating_count(),APICall.productDetailsModelList.get(i).getPrice(),APICall.productDetailsModelList.get(i).getRegular_price(),true));
                }
            }
        }
        System.out.println(categoryProductsModelList.size()+"***********");

        categoryProductsAdapter adapter=new categoryProductsAdapter(categoryProductsModelList);
        categoryProductRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
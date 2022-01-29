package com.birgunjbazar.mybazar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {
    private RecyclerView deliveryRecyclerView;
    private Button changeOrAddNewAddressButton;
    public static final int SELECT_ADDRESS=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        getSupportActionBar().setTitle("Delivery");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        deliveryRecyclerView =findViewById(R.id.delivery_recyclerView);
        changeOrAddNewAddressButton=findViewById(R.id.shippingDetails_changeOrAddButton);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryRecyclerView.setLayoutManager(layoutManager);
        List<cartItemModel> cartItemModelList=new ArrayList<>();
        cartItemModelList.add(new cartItemModel(0,R.drawable.sample,"Pixel 2",2,"Rs. 4999/-", "Rs. 5999/-",1,0,0));
        cartItemModelList.add(new cartItemModel(0,R.drawable.sample,"Pixel 2",2,"Rs. 4999/-", "Rs. 5999/-",1,1,0));
        cartItemModelList.add(new cartItemModel(0,R.drawable.sample,"Pixel 2",2,"Rs. 4999/-", "Rs. 5999/-",1,2,0));
        cartItemModelList.add(new cartItemModel(1,3,"Rs. 16999/-","Free","Rs. 16999/-","Rs. 5999/-"));

        cartAdapter cartAdapter=new cartAdapter(cartItemModelList);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeOrAddNewAddressButton.setVisibility(View.VISIBLE);
        changeOrAddNewAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addressIntent=new Intent(DeliveryActivity.this,MyAddressesActivity.class);
                addressIntent.putExtra("MODE",SELECT_ADDRESS);
                startActivity(addressIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
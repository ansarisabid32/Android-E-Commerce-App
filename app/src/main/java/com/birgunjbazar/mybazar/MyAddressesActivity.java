package com.birgunjbazar.mybazar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.birgunjbazar.mybazar.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {
    private RecyclerView myAddressesRecyclerView;
    private static AddressesAdapter addressesAdapter;
    private Button deliverHereButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_addresses);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAddressesRecyclerView=findViewById(R.id.myAddresses_addressesRecyclerView);
        deliverHereButton=findViewById(R.id.myAddresses_deliverHereButton);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myAddressesRecyclerView.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList=new ArrayList<>();
        addressesModelList.add(new AddressesModel("Sabid Ansari","lane number 1A, new Satnampura, Phagwara,Punjab","144402",true));
        addressesModelList.add(new AddressesModel("Sahid Ansari","lane number 1A, new Satnampura, Phagwara,Punjab","144402",false));
        addressesModelList.add(new AddressesModel("Ashok Kumar","lane number 1A, new Satnampura, Phagwara,Punjab","144402",false));
        addressesModelList.add(new AddressesModel("Sabid Ansari","lane number 1A, new Satnampura, Phagwara,Punjab","144402",false));
        addressesModelList.add(new AddressesModel("Sabid Ansari","lane number 1A, new Satnampura, Phagwara,Punjab","144402",false));
        addressesModelList.add(new AddressesModel("Vaibhav Ameta","lane number 1A, new Satnampura, Phagwara,Punjab","144402",false));
        addressesModelList.add(new AddressesModel("Tarun Singh Mahar","lane number 1A, new Satnampura, Phagwara,Punjab","144402",false));

        int mode=getIntent().getIntExtra("MODE",-1);
        if(mode==SELECT_ADDRESS){
            deliverHereButton.setVisibility(View.VISIBLE);
        }else{
            deliverHereButton.setVisibility(View.GONE);
        }
        addressesAdapter=new AddressesAdapter(addressesModelList,mode);
        myAddressesRecyclerView.setAdapter(addressesAdapter);
        ((SimpleItemAnimator)myAddressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressesAdapter.notifyDataSetChanged();
    }
    public static void refreshItem(int deselect,int select){
        addressesAdapter.notifyItemChanged(deselect);
        addressesAdapter.notifyItemChanged(select);
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
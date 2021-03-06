package com.birgunjbazar.mybazar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyCartFragment() {
        // Required empty public constructor
    }
    private RecyclerView cartItemsRecyclerView;
    private Button continueButton;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyCartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyCartFragment newInstance(String param1, String param2) {
        MyCartFragment fragment = new MyCartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_cart, container, false);
        cartItemsRecyclerView =view.findViewById(R.id.myCart_cartItemsRecyclerView);
        continueButton=view.findViewById(R.id.myCart_cartContinueButton);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);
        List<cartItemModel> cartItemModelList=new ArrayList<>();
        cartItemModelList.add(new cartItemModel(0,R.drawable.sample,"Pixel 2",2,"Rs. 4999/-", "Rs. 5999/-",1,0,0));
        cartItemModelList.add(new cartItemModel(0,R.drawable.sample,"Pixel 2",2,"Rs. 4999/-", "Rs. 5999/-",1,1,0));
        cartItemModelList.add(new cartItemModel(0,R.drawable.sample,"Pixel 2",2,"Rs. 4999/-", "Rs. 5999/-",1,2,0));
        cartItemModelList.add(new cartItemModel(1,3,"Rs. 16999/-","Free","Rs. 16999/-","Rs. 5999/-"));

        cartAdapter cartAdapter=new cartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent=new Intent(getContext(),AddAddressActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });
        return view;
    }
}
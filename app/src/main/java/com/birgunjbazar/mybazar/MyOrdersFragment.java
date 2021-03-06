package com.birgunjbazar.mybazar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyOrdersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyOrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyOrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyOrdersFragment newInstance(String param1, String param2) {
        MyOrdersFragment fragment = new MyOrdersFragment();
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
    private RecyclerView myOderRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_orders, container, false);
        myOderRecyclerView=view.findViewById(R.id.myOrders_recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myOderRecyclerView.setLayoutManager(layoutManager);

        List<myOrderItemModel> myOrderItemModelList= new ArrayList<>();
        myOrderItemModelList.add(new myOrderItemModel(R.drawable.sample,2,"Pixel 2XL (BLACK)","Delievered on Monday, 20th Jan 2021"));
        myOrderItemModelList.add(new myOrderItemModel(R.drawable.sample,1,"Samsung J7 Max(BLACK)","Delievered on Monday, 25th Jan 2021"));
        myOrderItemModelList.add(new myOrderItemModel(R.drawable.sample,0,"Pixel 4XL (BLACK)","Delievered on Monday, 19th Jan 2021"));
        myOrderItemModelList.add(new myOrderItemModel(R.drawable.sample,3,"Redmi 2XL (BLACK)","Delievered on Monday, 20th Feb 2021"));
        myOrderItemModelList.add(new myOrderItemModel(R.drawable.sample,5,"Poco 2XL (BLACK)","Cancelled"));

        myOrderItemAdapter myOrderItemAdapter=new myOrderItemAdapter(myOrderItemModelList);
        myOderRecyclerView.setAdapter(myOrderItemAdapter);
        myOrderItemAdapter.notifyDataSetChanged();

        return view;
    }
}
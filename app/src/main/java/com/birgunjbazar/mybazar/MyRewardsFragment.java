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
 * Use the {@link MyRewardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyRewardsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyRewardsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyRewardsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyRewardsFragment newInstance(String param1, String param2) {
        MyRewardsFragment fragment = new MyRewardsFragment();
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

    private RecyclerView rewardsRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_rewards, container, false);
        rewardsRecyclerView=view.findViewById(R.id.myRewards_recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rewardsRecyclerView.setLayoutManager(layoutManager);

        List<RewardsModel> rewardsModelList=new ArrayList<>();
        rewardsModelList.add(new RewardsModel("Cashback","till 10th Feb 2021","GET 20% CASHBACK on any product above Rs. 200/- and below Rs. 1000/-."));
        rewardsModelList.add(new RewardsModel("DISCOUNT","till 5th Feb 2021","GET 10% DISCOUNT on any product above Rs. 2000/- and below Rs. 3000/-."));
        rewardsModelList.add(new RewardsModel("Buy 1 Get 1 Free","till 9th Feb 2021","GET 15% CASHBACK on any product above Rs. 5000/- and below Rs. 10000/-."));
        rewardsModelList.add(new RewardsModel("Cashback","till 20th Feb 2021","GET 20% CASHBACK on any product above Rs. 500/- and below Rs.5000/-."));
        MyRewardsAdapter myRewardsAdapter=new MyRewardsAdapter(rewardsModelList,false);
        rewardsRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        return view;
    }
}
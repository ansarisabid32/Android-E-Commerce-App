package com.birgunjbazar.mybazar;

import android.content.Intent;
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
 * Use the {@link MyAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyAccountFragment newInstance(String param1, String param2) {
        MyAccountFragment fragment = new MyAccountFragment();
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

    private RecyclerView profileDetails_recyclerView;
    public static final int MANAGE_ADDRESS=1;
    private static View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_account, container, false);
        profileDetails_recyclerView=view.findViewById(R.id.myAccount_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        profileDetails_recyclerView.setLayoutManager(layoutManager);

        List<ProfileDetailsModel> profileDetailsModelList=new ArrayList<>();
        profileDetailsModelList.add(new ProfileDetailsModel("My Addresses","view all saved addresses here","VIEW ALL ADDRESSES"));
        profileDetailsModelList.add(new ProfileDetailsModel("My Orders","view all your orders here","VIEW ALL ORDERS"));

        ProfileDetailsAdapter profileDetailsAdapter=new ProfileDetailsAdapter(profileDetailsModelList);
        profileDetails_recyclerView.setAdapter(profileDetailsAdapter);
        profileDetailsAdapter.notifyDataSetChanged();

        return view;
    }

    public static void launchAddressActivity(){
        Intent myAddressIntent=new Intent(view.getContext(),MyAddressesActivity.class);
        myAddressIntent.putExtra("MODE",MANAGE_ADDRESS);
        view.getContext().startActivity(myAddressIntent);
    }
}
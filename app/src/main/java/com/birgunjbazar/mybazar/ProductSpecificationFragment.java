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
 * Use the {@link ProductSpecificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductSpecificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductSpecificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductSpecificationFragment newInstance(String param1, String param2) {
        ProductSpecificationFragment fragment = new ProductSpecificationFragment();
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
    private RecyclerView productSpecificationRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_specification, container, false);
        productSpecificationRecyclerView=view.findViewById(R.id.productSpecification_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productSpecificationRecyclerView.setLayoutManager(linearLayoutManager);
        List<ProductSpecificationModel> productSpecificationModelList=new ArrayList<>();
        productSpecificationModelList.add(new ProductSpecificationModel(0,"General"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram2","2GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram3","1GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram4","4GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram5","5GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"Others"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram6","1GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram7","2GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram8","7GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram9","8GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram10","12GB"));


        ProductSpecificationAdapter productSpecificationAdapter=new ProductSpecificationAdapter(productSpecificationModelList);
        productSpecificationRecyclerView.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();
        return view;
    }
}
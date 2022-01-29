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
 * Use the {@link MyWishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyWishlistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyWishlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyWishlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyWishlistFragment newInstance(String param1, String param2) {
        MyWishlistFragment fragment = new MyWishlistFragment();
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
    private RecyclerView myWishlistRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_wishlist, container, false);
        myWishlistRecyclerView=view.findViewById(R.id.myWishlist_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        myWishlistRecyclerView.setLayoutManager(linearLayoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();
//        wishlistModelList.add(new WishlistModel("","Samsung J7 Max",2, "2.5",14,"Rs. 5999/-","Rs. 2999/-",true));
//        wishlistModelList.add(new WishlistModel("","Samsung J7 Prime",0, "3.5",24,"Rs. 8999/-","Rs. 3999/-",true));
//        wishlistModelList.add(new WishlistModel("","Samsung J3",4, "4",9,"Rs. 15999/-","Rs. 3999/-",true));
//        wishlistModelList.add(new WishlistModel("","Redmi note 9",1, "5",4,"Rs. 12999/-","Rs. 1999/-",false));
//        wishlistModelList.add(new WishlistModel("","Oppo A7",0, "1.5",22,"Rs. 9999/-","Rs. 4999/-",true));
//        wishlistModelList.add(new WishlistModel("","Poco C3",3, "2",19,"Rs. 8999/-","Rs. 1999/-",false));
//        wishlistModelList.add(new WishlistModel("","Samsung J5",2, "3.5",16,"Rs. 7999/-","Rs. 2999/-",false));

        WishlistAdapter wishlistAdapter=new WishlistAdapter(wishlistModelList,true);
        myWishlistRecyclerView.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();
        return view;
    }
}
package com.birgunjbazar.mybazar.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.birgunjbazar.mybazar.APICall;
import com.birgunjbazar.mybazar.GridProductLayoutAdapter;
import com.birgunjbazar.mybazar.HomePageAdapter;
import com.birgunjbazar.mybazar.HomePageModel;
import com.birgunjbazar.mybazar.HorizontalProductScrollAdapter;
import com.birgunjbazar.mybazar.HorizontalProductScrollModel;
import com.birgunjbazar.mybazar.MainActivity;
import com.birgunjbazar.mybazar.R;
import com.birgunjbazar.mybazar.RegisterActivity;
import com.birgunjbazar.mybazar.SliderAdapter;
import com.birgunjbazar.mybazar.SliderModel;
import com.birgunjbazar.mybazar.URLHelper;
import com.birgunjbazar.mybazar.categoryAdapter;
import com.birgunjbazar.mybazar.categoryModel;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {
    private RecyclerView categoryRecyclerView,testing;
    private categoryAdapter categoryAdapter;
    public static List<categoryModel> categoryModelList;
    public static List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    ///banner slider
    ///

    /////Strip Ad

    //// Strip Ad

    /// Horizontal Product Layout
    ///

    ///Grid Product Layout
    ///
    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        categoryRecyclerView=view.findViewById(R.id.category_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<categoryModel>();

//        try {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url("https://www.birgunjbazar.com/demo/demo.php")
//                    .build();
//            Response responses = null;
//
//            try {
//                responses = client.newCall(request).execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String jsonData = responses.body().string();
//            JSONObject Jobject = new JSONObject(jsonData);
//            JSONArray Jarray = Jobject.getJSONArray("name");
//
//            for (int i = 0; i < Jarray.length(); i++) {
//                categoryModelList.add(new categoryModel("link",Jarray.getJSONObject(i).toString()));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        categoryModelList.add(new categoryModel("link","Home","-1"));
        new APICall().new updateCategoryList().execute(URLHelper.CATEGORY_URL);



//        categoryModelList.add(new categoryModel("link","Electronics"));
//        categoryModelList.add(new categoryModel("link","Furniture"));
//        categoryModelList.add(new categoryModel("link","Fashion"));
//        categoryModelList.add(new categoryModel("link","Toys"));
//        categoryModelList.add(new categoryModel("link","Sports"));
//        categoryModelList.add(new categoryModel("link","Mens"));

        categoryAdapter=new categoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        /////banner slider
        List<SliderModel> sliderModelList=new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.slider,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider2,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider3,"#077AE4"));
        sliderModelList.add(new SliderModel(R.drawable.slider4,"#077AE4"));

        /////

        ///Horizontal Product layout

        horizontalProductScrollModelList=new ArrayList<HorizontalProductScrollModel>();
        new APICall().new updateHorizontal().execute(URLHelper.HORIZONTAL_SCROLL_URL);

//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Redmi 5A", "SD 575 Processor", "Rs. 5999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "J7 Max", "SD 765 Processor", "Rs. 9999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Oppo A3", "SD 730g Processor", "Rs. 12999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Iphone 11", "SD 855 Processor", "Rs. 7999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Poco c3", "SD 675 Processor", "Rs. 19999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Infinx Hot 9 Pro", "SD 925 Processor", "Rs. 25999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Oppo A3", "SD 730g Processor", "Rs. 12999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Realme 2", "SD 660g Processor", "Rs. 10599"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Samsung J7", "SD 425g Processor", "Rs. 7999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "J7 Max", "SD 765 Processor", "Rs. 9999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Oppo A3", "SD 730g Processor", "Rs. 12999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Iphone 11", "SD 855 Processor", "Rs. 7999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Poco c3", "SD 675 Processor", "Rs. 19999"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel("","", "Infinx Hot 9 Pro", "SD 925 Processor", "Rs. 25999"));
//
        //new updateHorizontal().execute("https://www.birgunjbazar.com/demo/getProducts.php?pageNo=1");
        ///

        ///Grid Product Layout

        ///

        /////Strip Ad

        //// Strip Ad


        /////test recycler view
        testing =view.findViewById(R.id.homePage_recyclerView);
        LinearLayoutManager testingLayoutManager =new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(sliderModelList,0));
        homePageModelList.add(new HomePageModel(2,"Sabid Ansari","#6ce650",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider2,"#000000"));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider,"#ffff00"));
        homePageModelList.add(new HomePageModel(3,"Sabid Ansari","#9b45d1",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider4,"#ff0000"));
        homePageModelList.add(new HomePageModel(2,"Sabid Ansari","#67a0f0",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Sabid Ansari","#67a0f0",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider2,"#000000"));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider,"#ffff00"));
        homePageModelList.add(new HomePageModel(3,"Sabid Ansari","#b3a57d",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.slider4,"#ff0000"));
        homePageModelList.add(new HomePageModel(2,"Sabid Ansari","#6cf0f5",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Sabid Ansari","#6cf0f5",horizontalProductScrollModelList));

        HomePageAdapter adapter =new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        /////
        for (int i=0;i<APICall.loadedCategoriesId.size();i++){
            String categoryID=APICall.loadedCategoriesId.get(i);
            new APICall().new updateProductsByCategory().execute((URLHelper.PRODUCTS_BY_CATEGORY_URL+categoryID));
        }
        return view;
    }

}
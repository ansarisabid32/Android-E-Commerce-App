package com.birgunjbazar.mybazar;

import android.content.Intent;
import android.content.UriMatcher;
import android.os.AsyncTask;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.birgunjbazar.mybazar.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APICall{
    public static List<WishlistModel> wishlistModelList=new ArrayList<>();
    public static List<List<categoryProductsModel>> lists=new ArrayList<>();
    public static List<String> loadedCategoriesId=new ArrayList<>();
    public static List<categoryProductsModel> wishlistModelListByCategory=new ArrayList<>();

    public List<String> categories=new ArrayList<>();
    public List<String> images=new ArrayList<>();

    public static List<productDetailsModel> productDetailsModelList=new ArrayList<>();

    public class updateCategoryList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String finalURL = strings[0];
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(finalURL)
                        .get()
                        .build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        if (HomeFragment.categoryModelList.size() != 0) {
                            HomeFragment.categoryModelList.clear();
                            loadedCategoriesId.clear();
                            HomeFragment.categoryModelList.add(new categoryModel("link", "Home", "-1"));
                            String result = response.body().string();
                            JSONArray Jarray = (JSONArray) new JSONArray(result);
                            for (int i = 0; i < Jarray.length(); i++) {
                                JSONObject object = Jarray.getJSONObject(i);
                                String tmp = object.get("name").toString();
                                String category_id = object.get("id").toString();
                                tmp = tmp.replace("&amp;", "&");
                                JSONObject forImage = (JSONObject) object.get("image");
                                HomeFragment.categoryModelList.add(new categoryModel(forImage.get("src").toString(), tmp, category_id));

                                //to get products by category
                                loadedCategoriesId.add(category_id);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public class updateProductsByCategory extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String finalURL = strings[0];

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(finalURL)
                        .get()
                        .build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        if(wishlistModelListByCategory.size()!=0){
                            wishlistModelListByCategory.clear();
                        }
                        JSONArray Jarray = (JSONArray) new JSONArray(result);
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject object = Jarray.getJSONObject(i);
                            String id = object.get("id").toString();
                            String name = object.get("name").toString();
                            String ratingTotal = object.get("average_rating").toString();
                            String ratingCount = (String) object.get("rating_count").toString();

                            String short_desc = Html.fromHtml(object.get("short_description").toString()).toString();
                            String price = object.get("price").toString();
                            String cuttedPrice = object.get("regular_price").toString();
                            name = name.replace("&amp;", "&");

                            JSONArray forImage1 = (JSONArray) object.getJSONArray("images");
                            JSONObject abc = forImage1.getJSONObject(0);
                            String imageurl = abc.get("src").toString();
                            wishlistModelListByCategory.add(new categoryProductsModel(imageurl, name, "0", ratingTotal, ratingCount, price, cuttedPrice, true));
                        }
                        lists.add(wishlistModelListByCategory);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public class updateHorizontal extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String finalURL = strings[0];

            try {
//                OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                builder.connectTimeout(1, TimeUnit.MINUTES) // connect timeout
//                        .writeTimeout(1, TimeUnit.MINUTES) // write timeout
//                        .readTimeout(1, TimeUnit.MINUTES); // read timeout
                OkHttpClient okHttpClient = new OkHttpClient(); //builder.build();
                Request request = new Request.Builder()
                        .url(finalURL)
                        .get()
                        .build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        JSONArray Jarray = (JSONArray) new JSONArray(result);
                        HomeFragment.horizontalProductScrollModelList.clear();
                        if (wishlistModelList.size() != 0) {
                            wishlistModelList.clear();
                        }
                        for (int i = 0; i < Jarray.length(); i++) {
                            categories.clear();
                            images.clear();
                            JSONObject object = Jarray.getJSONObject(i);
                            String id = object.get("id").toString();
                            String name = object.get("name").toString();
                            String status = object.get("status").toString();
                            String featured=object.get("featured").toString();
                            String ratingTotal = object.get("average_rating").toString();
                            String ratingCount = (String) object.get("rating_count").toString();
                            String description = Html.fromHtml(object.get("description").toString()).toString();
                            String short_description = Html.fromHtml(object.get("short_description").toString()).toString();
                            String price = object.get("price").toString();
                            String cuttedPrice = object.get("regular_price").toString();
                            name = name.replace("&amp;", "&");
                            String on_sale=object.get("on_sale").toString();
                            String stock_status=object.get("stock_status").toString();
                            //System.out.println(name+"  ---->  "+price+"  ---->  "+short_desc);
                            JSONArray forcat1 = (JSONArray) object.getJSONArray("categories");
                            for (int j=0;j<forcat1.length();j++){
                                JSONObject cat = forcat1.getJSONObject(j);
                                String category = cat.get("name").toString();
                                categories.add(category);
                            }

                            JSONArray forImage1 = (JSONArray) object.getJSONArray("images");
                            for (int j=0;j<forImage1.length();j++){
                                JSONObject abc = forImage1.getJSONObject(j);
                                String imageurl = abc.get("src").toString();
                                images.add(imageurl);
                            }

                            //System.out.println(imageurl);
                            //JSONObject forImage = (JSONObject) forImage1.get("0");
                            //categoryModelList.add(new categoryModel(forImage.get("src").toString(), name));
                            //System.out.println(forImage1.get("src").toString());
                            //System.out.println(price);
                            HomeFragment.horizontalProductScrollModelList.add(new HorizontalProductScrollModel(id, images.get(0), name, short_description, price));
                            //System.out.println(imageurl+"--->"+name+"--->"+ratingTotal+"--->"+price+"--->"+cuttedPrice);

                            wishlistModelList.add(new WishlistModel(images.get(0), name, "0", ratingTotal, ratingCount, price, cuttedPrice, true));
                            productDetailsModelList.add(new productDetailsModel(id,name,status,featured,description,short_description,price,cuttedPrice,on_sale,stock_status,ratingTotal,ratingCount,categories,images));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public class getAllProducts extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String finalURL = strings[0];
            try {
//                OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                builder.connectTimeout(1, TimeUnit.MINUTES) // connect timeout
//                        .writeTimeout(1, TimeUnit.MINUTES) // write timeout
//                        .readTimeout(1, TimeUnit.MINUTES); // read timeout
                OkHttpClient okHttpClient = new OkHttpClient(); //builder.build();
                Request request = new Request.Builder()
                        .url(finalURL)
                        .get()
                        .build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        JSONArray Jarray =(JSONArray) new JSONArray(result);
                        HomeFragment.horizontalProductScrollModelList.clear();
                        for (int i = 0; i < Jarray.length(); i++) {
                            JSONObject object = Jarray.getJSONObject(i);
                            String id = object.get("id").toString();
                            String name = object.get("name").toString();

                            String short_desc = Html.fromHtml(object.get("short_description").toString()).toString();
                            String price = object.get("price").toString();
                            name = name.replace("&amp;", "&");
                            //System.out.println(name+"  ---->  "+price+"  ---->  "+short_desc);

                            JSONArray forImage1 =(JSONArray) object.getJSONArray("images");
                            JSONObject abc=forImage1.getJSONObject(0);
                            String imageurl=abc.get("src").toString();

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
package com.birgunjbazar.mybazar;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @Override
    public int getCount() {
        /*if(horizontalProductScrollModelList.size()>4)
        {
            return 4;
        }
        else
        {

        }*/
        return horizontalProductScrollModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView==null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            //view.setElevation(0);
            //view.setBackgroundColor(Color.parseColor("#ffffff"));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent=new Intent(parent.getContext(),ProductDetailsActivity.class);
                    parent.getContext().startActivity(productDetailsIntent);
                }
            });

            ImageView productImage=(ImageView) view.findViewById(R.id.h_s_product_image);
            TextView productTitle=(TextView)view.findViewById(R.id.h_s_product_title);
            TextView productDescription=(TextView)view.findViewById(R.id.h_s_product_description);
            TextView productPrice=(TextView)view.findViewById(R.id.h_s_product_price);

            //productImage.setImageResource(horizontalProductScrollModelList.get(position).getProductImage());
            String resource=horizontalProductScrollModelList.get(position).getProductImage();
            if(!resource.equals("null")) {
                Glide.with(parent.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.ic_home)).into(productImage);
            }
            productTitle.setText(horizontalProductScrollModelList.get(position).getProductTitle());
            productDescription.setText(horizontalProductScrollModelList.get(position).getProductDescription());
            productPrice.setText("Rs. "+horizontalProductScrollModelList.get(position).getProductPrice()+"/-");
        }
        else
        {
            view=convertView;
        }
        return view;
    }
}

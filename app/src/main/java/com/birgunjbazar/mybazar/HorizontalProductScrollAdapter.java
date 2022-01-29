package com.birgunjbazar.mybazar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Random;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {
    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //int position= new Random().nextInt(horizontalProductScrollModelList.size());
        String resource=horizontalProductScrollModelList.get(position).getProductImage();
        String title=horizontalProductScrollModelList.get(position).getProductTitle();
        String description=horizontalProductScrollModelList.get(position).getProductDescription();
        String price=horizontalProductScrollModelList.get(position).getProductPrice();

        holder.setData(resource,title,description,price);

    }

    @Override
    public int getItemCount() {
        if(horizontalProductScrollModelList.size()>8){
            return 8;
        }
        else{
            return horizontalProductScrollModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle,productDescription,productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=(ImageView) itemView.findViewById(R.id.h_s_product_image);
            productTitle=(TextView)itemView.findViewById(R.id.h_s_product_title);
            productDescription=(TextView)itemView.findViewById(R.id.h_s_product_description);
            productPrice=(TextView)itemView.findViewById(R.id.h_s_product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent=new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
        private void setData(String resource,String title,String description,String price){
            if(!resource.equals("null")) {
                Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.ic_home)).into(productImage);
            }
            productTitle.setText(title);
            productDescription.setText(description);
            productPrice.setText("Rs. "+price+"/-");
        }
    }
}

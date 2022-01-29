package com.birgunjbazar.mybazar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myOrderItemAdapter extends RecyclerView.Adapter<myOrderItemAdapter.ViewHolder> {
    private List<myOrderItemModel> myOrderItemModelList;

    public myOrderItemAdapter(List<myOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public myOrderItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myOrderItemAdapter.ViewHolder holder, int position) {
        int resource=myOrderItemModelList.get(position).getProductImage();
        int rating=myOrderItemModelList.get(position).getRating();
        String title=myOrderItemModelList.get(position).getProductTitle();
        String deliveryDate=myOrderItemModelList.get(position).getDeliveryStatus();
        holder.setData(resource,rating,title,deliveryDate);
    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImage,deliveryIndicator;
        private TextView productTitle,deliveryStatus;
        private LinearLayout rateNowLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.myOrderItemLayout_productImage);
            deliveryIndicator=itemView.findViewById(R.id.myOrderItemLayout_indicator);
            productTitle=itemView.findViewById(R.id.myOrderItemLayout_productTitle);
            deliveryStatus=itemView.findViewById(R.id.myOrderItemLayout_delieveredDate);
            rateNowLayout=(LinearLayout)itemView.findViewById(R.id.rating_rateNowContainer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderDetailsIntent=new Intent(itemView.getContext(),OrderDetailsActivity.class);
                    itemView.getContext().startActivity(orderDetailsIntent);
                }
            });
        }
        private void setData(int resource,int rating,String title,String status){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(status.equals("Cancelled")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.red)));
                }
            }
            else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    deliveryIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.green)));
                }
            }
            deliveryStatus.setText(status);

            ///////rating layout
            setRating(rating);
            for (int x=0;x<rateNowLayout.getChildCount();x++){
                final int starPosition=x;
                rateNowLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRating(starPosition);
                    }
                });
            }

            ///////rating layout
        }
        private void setRating(int starPosition) {
            for (int x=0;x<rateNowLayout.getChildCount();x++){
                ImageView starBtn =(ImageView)rateNowLayout.getChildAt(x);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                }
                if(x<=starPosition){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                    }
                }
            }
        }
    }
}

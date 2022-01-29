package com.birgunjbazar.mybazar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {
    List<WishlistModel> wishlistModelList;
    private boolean wishlist;

    public WishlistAdapter(List<WishlistModel> wishlistModelList, boolean wishlist) {
        this.wishlistModelList = wishlistModelList;
        this.wishlist = wishlist;
    }

    @NonNull
    @Override
    public WishlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.ViewHolder holder, int position) {
        String resouce=wishlistModelList.get(position).getProductImage();
        String title=wishlistModelList.get(position).getProductTitle();
        String freeCoupons=wishlistModelList.get(position).getFreeCoupons();
        String rating=wishlistModelList.get(position).getRating();
        String totalRating=wishlistModelList.get(position).getTotalRatings();
        String productPrice=wishlistModelList.get(position).getProductPrice();
        String productCuttedPrice=wishlistModelList.get(position).getCuttedProductPrice();
        boolean paymentMethod=wishlistModelList.get(position).isCOD();
        holder.setData(resouce,title,freeCoupons,rating,totalRating,productPrice,productCuttedPrice,paymentMethod);
    }

    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCoupons;
        private ImageView couponIcon;
        private TextView rating;
        private TextView totalRatings;
        private TextView productPrice;
        private TextView productCuttedPrice;
        private View priceCut;
        private TextView paymentMethod;
        private ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.wishlistItem_productImage);
            productTitle=itemView.findViewById(R.id.wishlistItem_productTitle);
            freeCoupons=itemView.findViewById(R.id.wishlistItem_freeCoupon);
            couponIcon=itemView.findViewById(R.id.wishlistItem_couponIcon);
            rating=itemView.findViewById(R.id.wishlistItem_rating);
            totalRatings=itemView.findViewById(R.id.wishlistItem_totalRating);
            productPrice=itemView.findViewById(R.id.wishlistItem_productPrice);
            productCuttedPrice=itemView.findViewById(R.id.wishlistItem_productCuttedPrice);
            priceCut=itemView.findViewById(R.id.wishlistItem_divider);
            paymentMethod=itemView.findViewById(R.id.wishlistItem_paymentMethod);
            deleteButton=itemView.findViewById(R.id.wishlistItem_deleteButton);
        }

        private void setData(String resource,String title,String freeCouponsNumber,String averageRating,String totalRatingsNumber,String price,String cuttedPrice,boolean payMethod){
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.sample)).into(productImage);
            productTitle.setText(title);
            if(!freeCouponsNumber.equals("0")){
                couponIcon.setVisibility(View.VISIBLE);
                if(freeCouponsNumber.equals("1")){
                    freeCoupons.setText(freeCouponsNumber+" free coupon");
                }else{
                    freeCoupons.setText(freeCouponsNumber+" free coupons");
                }
            }else {
                couponIcon.setVisibility(View.INVISIBLE);
                freeCoupons.setVisibility(View.INVISIBLE);
            }
            rating.setText(String.valueOf(averageRating.charAt(0)));
            if(!totalRatingsNumber.equals("0")){
                if(totalRatingsNumber.equals("1")){
                    totalRatings.setText(String.valueOf(totalRatingsNumber.charAt(0))+" (rating)");
                }else{
                    totalRatings.setText(String.valueOf(totalRatingsNumber.charAt(0))+" (ratings)");
                }
            }else{
                totalRatings.setVisibility(View.INVISIBLE);
            }
            productPrice.setText("Rs. "+price+"/-");
            productCuttedPrice.setText("Rs. "+cuttedPrice+"/-");
            if(payMethod){
                paymentMethod.setVisibility(View.VISIBLE);
                paymentMethod.setText("Cash on Delivery Available");
            }
            else{
                paymentMethod.setVisibility(View.GONE);
            }
            if(wishlist){
                deleteButton.setVisibility(View.VISIBLE);
            }else{
                deleteButton.setVisibility(View.GONE);
            }
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "delete working", Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailIntent=new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailIntent);
                }
            });
        }
    }
}

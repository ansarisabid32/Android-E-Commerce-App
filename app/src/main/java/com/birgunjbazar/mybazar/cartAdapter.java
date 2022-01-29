package com.birgunjbazar.mybazar;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter {

    private List<cartItemModel> cartItemModelList;

    public cartAdapter(List<cartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){
            case 0:
                return cartItemModel.CART_ITEM;
            case 1:
                return cartItemModel.TOTAL_AMOUNT;
            default: return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case cartItemModel.CART_ITEM:
                View cartItemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                return new cartItemViewHolder(cartItemView);
            case cartItemModel.TOTAL_AMOUNT:
                View cartTotalView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout,parent,false);
                return new cartTotalAmountViewHolder(cartTotalView);
            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (cartItemModelList.get(position).getType()){
            case cartItemModel.CART_ITEM:
                int resource=cartItemModelList.get(position).getProductImage();
                String title=cartItemModelList.get(position).getProductTitle();
                int freecoupons=cartItemModelList.get(position).getFreeCoupon();
                String productPrice=cartItemModelList.get(position).getProductPrice();
                String productCuttedPrice=cartItemModelList.get(position).getProductCuttedPrice();
                int offersApplied=cartItemModelList.get(position).getOffersApplied();
                ((cartItemViewHolder)holder).setCartItemDetails(resource,title,freecoupons,productPrice,productCuttedPrice,offersApplied);
                break;
            case cartItemModel.TOTAL_AMOUNT:
                int totalItems=cartItemModelList.get(position).getTotalItem();
                String totalItemPrice=cartItemModelList.get(position).getTotalItemPrice();
                String deliveryPrice=cartItemModelList.get(position).getDeliveryPrice();
                String totalAmount=cartItemModelList.get(position).getTotalAmount();
                String savedAmount=cartItemModelList.get(position).getSavedAmount();
                ((cartTotalAmountViewHolder)holder).setCartTotalAmountDetails(totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount);
                break;
            default: return;
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class cartItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImage,freeCouponImage;
        private TextView productTitle,freeCoupon,productPrice,cuttedPrice,offerApplied,couponApplied,productQuantity;
        public cartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=(ImageView)itemView.findViewById(R.id.cartItemLayout_productImage);
            freeCouponImage=(ImageView)itemView.findViewById(R.id.cartItemLayout_freeCouponIcon);
            productTitle=(TextView)itemView.findViewById(R.id.cartItemLayout_productTitle);
            freeCoupon=(TextView)itemView.findViewById(R.id.cartItemLayout_freeCoupon);
            productPrice=(TextView)itemView.findViewById(R.id.cartItemLayout_productPrice);
            cuttedPrice=(TextView)itemView.findViewById(R.id.cartItemLayout_cuttedPrice);
            offerApplied=(TextView)itemView.findViewById(R.id.cartItemLayout_offersApplied);
            couponApplied=(TextView)itemView.findViewById(R.id.cartItemLayout_couponsApplied);
            productQuantity=(TextView)itemView.findViewById(R.id.cartItemLayout_productQuantity);
        }
        private void setCartItemDetails(int resource,String title,int freeCouponNumber,String productPriceText,String cuttedPriceText,int offerAppliedNumber/*,int couponAppliedNumber,int productQuantity*/)
        {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(freeCouponNumber>0){
                freeCouponImage.setVisibility(View.VISIBLE);
                freeCoupon.setVisibility(View.VISIBLE);
                if(freeCouponNumber==1){
                    freeCoupon.setText("free "+freeCouponNumber+" Coupon");
                }
                else
                {
                    freeCoupon.setText("free "+freeCouponNumber+" Coupons");
                }
            }
            else
            {
                freeCouponImage.setVisibility(View.INVISIBLE);
                freeCoupon.setVisibility(View.INVISIBLE);
            }
            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);
            if(offerAppliedNumber>0) {
                offerApplied.setVisibility(View.VISIBLE);
                if(offerAppliedNumber==1){
                    offerApplied.setText(offerAppliedNumber+" Offer Applied");
                }else{
                    offerApplied.setText(offerAppliedNumber+" Offers Applied");

                }
            }else{
                offerApplied.setVisibility(View.INVISIBLE);
            }/*
            if(couponAppliedNumber>0) {
                couponApplied.setVisibility(View.VISIBLE);
                if(couponAppliedNumber==1){
                    offerApplied.setText(couponAppliedNumber+" Coupon Applied");
                }else{
                    couponApplied.setText(couponAppliedNumber+" Coupons Applied");

                }
            }else{
                couponApplied.setVisibility(View.INVISIBLE);
            }*/
            productQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final Dialog quantityDialog=new Dialog(itemView.getContext());
                    quantityDialog.setContentView(R.layout.quantity_dialog);
                    quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    quantityDialog.setCancelable(false);
                    final EditText quantityNumber=quantityDialog.findViewById(R.id.quantityDialog_quantityNumber);
                    Button cancelButton=quantityDialog.findViewById(R.id.quantityDialog_cancelButton);
                    Button okButton=quantityDialog.findViewById(R.id.quantityDialog_okButton);

                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            quantityDialog.dismiss();
                        }
                    });
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!quantityNumber.getText().toString().isEmpty()){
                                productQuantity.setText("Qty: "+ quantityNumber.getText());
                            }
                            quantityDialog.dismiss();
                        }
                    });
                    quantityDialog.show();
                }
            });
        }
    }
    class cartTotalAmountViewHolder extends RecyclerView.ViewHolder {
        private TextView totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount;
        public cartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
            totalItems=(TextView)itemView.findViewById(R.id.cartTotalAmount_totalItems);
            totalItemPrice=(TextView)itemView.findViewById(R.id.cartTotalAmount_totalItemPrice);
            deliveryPrice=(TextView)itemView.findViewById(R.id.cartTotalAmount_deliveryPrice);
            totalAmount=(TextView)itemView.findViewById(R.id.cartTotalAmount_totalPrice);
            savedAmount=(TextView)itemView.findViewById(R.id.cartTotalAmount_savedAmount);
        }
        private void setCartTotalAmountDetails(int totalItemsNumber,String totalItemPriceNumber, String deliverypriceNumber,String totalAmountNumber,String savedAmountNumber){
            if(totalItemsNumber==1){
                totalItems.setText("Price ("+totalItemsNumber+" item)");
            }
            else{
                totalItems.setText("Price ("+totalItemsNumber+" items)");
            }
            totalItemPrice.setText(totalItemPriceNumber);
            deliveryPrice.setText(deliverypriceNumber);
            totalAmount.setText(totalAmountNumber);
            savedAmount.setText(savedAmountNumber);
        }
    }
}

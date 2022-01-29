package com.birgunjbazar.mybazar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.birgunjbazar.mybazar.DeliveryActivity.SELECT_ADDRESS;
import static com.birgunjbazar.mybazar.MyAccountFragment.MANAGE_ADDRESS;
import static com.birgunjbazar.mybazar.MyAddressesActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.Viewholder> {
    private List<AddressesModel> addressesModelList;
    private int MODE;
    private int preSelectedPosition = -1;

    public AddressesAdapter(List<AddressesModel> addressesModelList,int MODE) {
        this.addressesModelList = addressesModelList;
        this.MODE=MODE;
    }

    @NonNull
    @Override
    public AddressesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.addresses_item_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressesAdapter.Viewholder holder, int position) {
        String fullname=addressesModelList.get(position).getFullname();
        String address=addressesModelList.get(position).getAddress();
        String pincode=addressesModelList.get(position).getPincode();
        Boolean check=addressesModelList.get(position).getSelected();
        holder.setData(fullname,address,pincode,check,position);

    }

    @Override
    public int getItemCount() {
        return addressesModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private TextView addressesFullName,addressesAddress,addressesPincode;
        private ImageView icon;
        private LinearLayout optionContainer;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            addressesFullName=itemView.findViewById(R.id.addressesItem_name);
            addressesAddress=itemView.findViewById(R.id.addressesItem_address);
            addressesPincode=itemView.findViewById(R.id.addressesItem_pincode);
            icon=itemView.findViewById(R.id.addressesItem_iconView);
            optionContainer=itemView.findViewById(R.id.addressItem_optionContainer);
        }
        private void setData(String fullname,String address,String pincode,Boolean check,int position){
            addressesFullName.setText(fullname);
            addressesAddress.setText(address);
            addressesPincode.setText(pincode);

            if(MODE==SELECT_ADDRESS){
                icon.setImageResource(R.drawable.ic_check);
                if(check){
                    icon.setVisibility(View.VISIBLE);
                    preSelectedPosition=position;
                }else {
                    icon.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(preSelectedPosition!=position) {
                            addressesModelList.get(position).setSelected(true);
                            addressesModelList.get(preSelectedPosition).setSelected(false);
                            refreshItem(preSelectedPosition, position);
                            preSelectedPosition = position;
                        }
                    }
                });
            }
            else if(MODE==MANAGE_ADDRESS){
                optionContainer.setVisibility(View.GONE);
                icon.setImageResource(R.drawable.ic_dott_selector);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        optionContainer.setVisibility(View.VISIBLE);
                        refreshItem(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition=position;
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshItem(preSelectedPosition,preSelectedPosition);
                        preSelectedPosition = -1;
                    }
                });
            }
        }
    }
}

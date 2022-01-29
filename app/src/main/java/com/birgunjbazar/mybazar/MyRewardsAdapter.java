package com.birgunjbazar.mybazar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRewardsAdapter extends RecyclerView.Adapter<MyRewardsAdapter.ViewHolder> {
    private List<RewardsModel> rewardsModelList;
    private Boolean useMiniLayout=false;

    public MyRewardsAdapter(List<RewardsModel> rewardsModelList, Boolean useMiniLayout) {
        this.rewardsModelList = rewardsModelList;
        this.useMiniLayout=useMiniLayout;
    }

    @NonNull
    @Override
    public MyRewardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(useMiniLayout){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_rewards_item_layout, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRewardsAdapter.ViewHolder holder, int position) {
        String title=rewardsModelList.get(position).getRewardTitle();
        String validity=rewardsModelList.get(position).getRewardValidity();
        String body=rewardsModelList.get(position).getRewardBody();
        holder.setData(title,validity,body);

    }

    @Override
    public int getItemCount() {
        return rewardsModelList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView rewardsTitle,rewardsExpiryDate,rewardsBody;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rewardsTitle=itemView.findViewById(R.id.rewardsItem_couponTitle);
            rewardsExpiryDate=itemView.findViewById(R.id.rewardsItem_couponValidity);
            rewardsBody=itemView.findViewById(R.id.rewardsItem_couponBody);
        }
        private void setData(String couponTitle,String couponValidity,String couponBody){
            rewardsTitle.setText(couponTitle);
            rewardsExpiryDate.setText(couponValidity);
            rewardsBody.setText(couponBody);

            if(useMiniLayout){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProductDetailsActivity.couponTitle.setText(couponTitle);
                        ProductDetailsActivity.couponBody.setText(couponBody);
                        ProductDetailsActivity.couponExpiryDate.setText(couponValidity);
                        ProductDetailsActivity.showDialogRecyclerView();
                    }
                });
            }
        }
    }
}

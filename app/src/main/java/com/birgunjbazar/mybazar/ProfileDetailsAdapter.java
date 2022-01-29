package com.birgunjbazar.mybazar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.birgunjbazar.mybazar.MyAccountFragment.launchAddressActivity;

public class ProfileDetailsAdapter extends RecyclerView.Adapter<ProfileDetailsAdapter.ViewHolder> {
    private List<ProfileDetailsModel> profileDetailsModelList;

    public ProfileDetailsAdapter(List<ProfileDetailsModel> profileDetailsModelList) {
        this.profileDetailsModelList = profileDetailsModelList;
    }

    @NonNull
    @Override
    public ProfileDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_details_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileDetailsAdapter.ViewHolder holder, int position) {
        String title=profileDetailsModelList.get(position).getTitle();
        String body=profileDetailsModelList.get(position).getBody();
        String viewAll=profileDetailsModelList.get(position).getViewAll();
        holder.setData(title,body,viewAll);

    }

    @Override
    public int getItemCount() {
        return profileDetailsModelList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView profileDetailsTitle,profileDetailsBody,profileDetailsViewAll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileDetailsTitle=itemView.findViewById(R.id.profileDetails_title);
            profileDetailsBody=itemView.findViewById(R.id.profileDetails_body);
            profileDetailsViewAll=itemView.findViewById(R.id.profileDetails_viewAll);
        }
        private void setData(String title,String body,String viewAll){
            profileDetailsTitle.setText(title);
            profileDetailsBody.setText(body);
            profileDetailsViewAll.setText(viewAll);
            profileDetailsViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewAll.equals("VIEW ALL ADDRESSES")){
                        Toast.makeText(itemView.getContext(), "View all orders clicked", Toast.LENGTH_SHORT).show();
                        launchAddressActivity();
                    }
                }
            });
        }
    }
}

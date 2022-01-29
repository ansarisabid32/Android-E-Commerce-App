package com.birgunjbazar.mybazar;

import android.content.Intent;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.birgunjbazar.mybazar.ui.home.HomeFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {

    private List<categoryModel> categoryModelList;

    public categoryAdapter(List<categoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public categoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.ViewHolder holder, int position) {
        String icon=categoryModelList.get(position).getCategoryIconLink();
        String name=categoryModelList.get(position).getCategoryName();
        String categoryID=categoryModelList.get(position).getCategory_id();
        holder.setCategory(name,position,categoryID);
        holder.setCategoryIcon(icon);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView categoryIcon;
        private TextView categoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon=(ImageView)itemView.findViewById(R.id.category_icon);
            categoryName=(TextView)itemView.findViewById(R.id.category_name);

        }
        private void setCategoryIcon(String iconurl){
            if(!iconurl.equals("null")) {
                Glide.with(itemView.getContext()).load(iconurl).apply(new RequestOptions().placeholder(R.drawable.ic_home)).into(categoryIcon);
            }
        }
        private void setCategory(final String name,final int position,String categoryID){
            categoryName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position!=0) {
//                        if(HomeFragment.categoryModelList.size()>1){
//                            for (int i=1;i<HomeFragment.categoryModelList.size();i++){
//                                //APICall.loadedCategoriesId.add(HomeFragment.categoryModelList.get(i).getCategory_id());
//                                String url= URLHelper.PRODUCTS_BY_CATEGORY_URL+APICall.loadedCategoriesId.get(i-1);
//                                System.out.println("++++++++"+url+"+++++++");
//                                new APICall().new updateProductsByCategory().execute(url);
//
//                            }
//                        }
//                        System.out.println("#############Lists:        "+APICall.lists.size()+"     ####################");
//                        //System.out.println("#############Category:  "+HomeFragment.categoryModelList.size()+"     ####################");
//                        System.out.println("################        "+APICall.loadedCategoriesId.size()+"     ####################");
//                        SystemClock.sleep(1000);

                        Intent categoryIntent = new Intent(itemView.getContext(), categoryProductsActivity.class);
                        categoryIntent.putExtra("CategoryName", name);
                        itemView.getContext().startActivity(categoryIntent);
                    }
                }
            });
        }
    }
}

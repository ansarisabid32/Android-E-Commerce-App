package com.birgunjbazar.mybazar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;


    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool=new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_LAYOUT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View viewBanner = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_layout, parent, false);
                return new bannerSliderViewHolder(viewBanner);
            case HomePageModel.STRIP_AD_BANNER:
                View viewStripAd = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_ad_layout, parent, false);
                return new stripAdViewHolder(viewStripAd);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View viewHorizontalProduct = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new horizontalProductViewHolder(viewHorizontalProduct);
            case HomePageModel.GRID_PRODUCT_LAYOUT:
                View gridProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductViewHolder(gridProductView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((bannerSliderViewHolder) holder).setBannerSliderViewPager(sliderModelList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                int resource = homePageModelList.get(position).getResource();
                String color = homePageModelList.get(position).getBackgroundColor();
                ((stripAdViewHolder) holder).setStripAdContainer(resource, color);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String bgColor=homePageModelList.get(position).getBackgroundColor();
                String horizontalTitle = homePageModelList.get(position).getTitle();
                List<WishlistModel> viewAllProductList=homePageModelList.get(position).getViewAllProductList();
                List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((horizontalProductViewHolder) holder).setHorizontalProductLayout(horizontalProductScrollModelList, horizontalTitle,bgColor,viewAllProductList);
                break;
            case HomePageModel.GRID_PRODUCT_LAYOUT:
                String gridbgColor=homePageModelList.get(position).getBackgroundColor();
                String gridTitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductScrollModel> gridProductScrollModelList = homePageModelList.get(position).getHorizontalProductScrollModelList();
                ((GridProductViewHolder) holder).setGridProductLayout(gridProductScrollModelList, gridTitle,gridbgColor);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    public class bannerSliderViewHolder extends RecyclerView.ViewHolder {
        private ViewPager bannerSliderViewPager;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<SliderModel> arrangedList;

        public bannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
        }

        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList) {
            currentPage=2;
            if(timer!=null){
                timer.cancel();
            }

            arrangedList=new ArrayList<>();
            for(int i=0;i<sliderModelList.size();i++){
                arrangedList.add(i,sliderModelList.get(i));
            }
            arrangedList.add(0,sliderModelList.get(sliderModelList.size()-2));
            arrangedList.add(1,sliderModelList.get(sliderModelList.size()-1));
            arrangedList.add(sliderModelList.get(0));
            arrangedList.add(sliderModelList.get(1));

            SliderAdapter sliderAdapter = new SliderAdapter(arrangedList);
            bannerSliderViewPager.setAdapter(sliderAdapter);

            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);
            bannerSliderViewPager.setCurrentItem(currentPage);
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }
                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
            startbannerSlideShow(arrangedList);
            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(arrangedList);
                    stopbannerSlideShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startbannerSlideShow(arrangedList);
                    }
                    return false;
                }
            });
        }

        private void pageLooper(List<SliderModel> sliderModelList) {
            if (currentPage == sliderModelList.size() - 2) {
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
        }

        private void startbannerSlideShow(List<SliderModel> sliderModelList) {
            Handler handler = new Handler();
            Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderModelList.size()) {
                        currentPage = 1;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopbannerSlideShow() {
            timer.cancel();
        }
    }

    public class stripAdViewHolder extends RecyclerView.ViewHolder {
        private ImageView stripAdImage;
        private ConstraintLayout stripAdContainer;

        public stripAdViewHolder(@NonNull View itemView) {
            super(itemView);
            stripAdImage = (ImageView) itemView.findViewById(R.id.strip_ad_image);
            stripAdContainer = (ConstraintLayout) itemView.findViewById(R.id.strip_ad_container);
        }

        private void setStripAdContainer(int resource, String color) {
            stripAdImage.setImageResource(resource);
            stripAdContainer.setBackgroundColor(Color.parseColor(color));
        }
    }

    public class horizontalProductViewHolder extends RecyclerView.ViewHolder {
        private TextView HorizontallayoutTitle;
        private Button HorizontalviewAllButton;
        private RecyclerView horizontalRecyclerView;
        private ConstraintLayout horizontalLayoutContainer;

        public horizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalLayoutContainer=itemView.findViewById(R.id.horizontal_scroll_layout_container);
            HorizontallayoutTitle = (TextView) itemView.findViewById(R.id.horizontal_scroll_layout_title);
            HorizontalviewAllButton = (Button) itemView.findViewById(R.id.horizontal_scroll_layout_viewAll);
            horizontalRecyclerView = (RecyclerView) itemView.findViewById(R.id.horizontal_scroll_layout_rv);
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);
        }

        private void setHorizontalProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title,String bgColor,List<WishlistModel> viewAllProductList) {
            HorizontallayoutTitle.setText(title);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                horizontalLayoutContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(bgColor)));
            }
            if (horizontalProductScrollModelList.size() > 8) {
                HorizontalviewAllButton.setVisibility(View.VISIBLE);
                HorizontalviewAllButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //System.out.println("-----------------"+APICall.wishlistModelList.size()+"------------------------");

                       // System.out.println("-----------------"+ViewAllActivity.wishlistModelList.size()+"------------------------");

                        Intent viewAllIntent=new Intent(itemView.getContext(),ViewAllActivity.class);
                        viewAllIntent.putExtra("Layout_code",0);
                        viewAllIntent.putExtra("title",title);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            } else {
                HorizontalviewAllButton.setVisibility(View.INVISIBLE);
            }
            HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);
            horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder {
        private TextView gridLayoutTitle;
        private Button gridLayoutViewAll;
        private GridLayout gridProductLayout;
        private ConstraintLayout gridProduct_Container;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
            gridProduct_Container=itemView.findViewById(R.id.gridProductLayout_container);
            gridLayoutTitle = (TextView) itemView.findViewById(R.id.grid_product_layout_title);
            gridLayoutViewAll = (Button) itemView.findViewById(R.id.grid_product_layout_viewAll);
            gridProductLayout=itemView.findViewById(R.id.grid_product_layout_gridlayout);
        }

        private void setGridProductLayout(List<HorizontalProductScrollModel> gridProductScrollModelList, String title,String bgColor) {
            gridLayoutTitle.setText(title);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                gridProduct_Container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(bgColor)));
            }
            //System.out.printf("*********************"+gridProductScrollModelList.size()+"***************************");
            if (gridProductScrollModelList.size() != 0) {
                for (int i = 0; i < 4; i++) {
                    ImageView productImage = gridProductLayout.getChildAt(i).findViewById(R.id.h_s_product_image);
                    TextView productTitle = gridProductLayout.getChildAt(i).findViewById(R.id.h_s_product_title);
                    TextView productDescription = gridProductLayout.getChildAt(i).findViewById(R.id.h_s_product_description);
                    TextView productPrice = gridProductLayout.getChildAt(i).findViewById(R.id.h_s_product_price);

                    //productImage.setImageResource(gridProductScrollModelList.get(i).getProductImage());
                    Glide.with(itemView.getContext()).load(gridProductScrollModelList.get(i).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.sample)).into(productImage);
                    productTitle.setText(gridProductScrollModelList.get(i).getProductTitle());
                    productDescription.setText(gridProductScrollModelList.get(i).getProductDescription());
                    productPrice.setText(gridProductScrollModelList.get(i).getProductPrice());

                    gridProductLayout.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
                    gridProductLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);

                            itemView.getContext().startActivity(productDetailsIntent);
                        }
                    });
                }
                gridLayoutViewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("Layout_code", 1);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            }
        }
    }
}

package com.example.viewpager;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class MainActivity2 extends YouTubeBaseActivity {

    private ViewPager viewPager;
    private SlideAdapter myadapter;
    String TAG = "MainPage";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myadapter = new SlideAdapter(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        viewPager.setAdapter(myadapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        floatingActionButton.setImageTintList(ColorStateList.valueOf(Color.WHITE));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected:position--: "+position );
                myadapter.notifyDataSetChanged();
                if (position == 3){
                    floatingActionButton.setImageResource(R.drawable.ic_baseline_check_24);
                    floatingActionButton.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                }else {
                    floatingActionButton.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                    floatingActionButton.setImageResource(R.drawable.ic_baseline_arrow_forward_24);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        floatingActionButton.setOnClickListener(v -> {
            Log.e(TAG, "onCreate:viewPager.getCurrentItem()-> "+viewPager.getCurrentItem() );

            myadapter.notifyDataSetChanged();
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        });

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}
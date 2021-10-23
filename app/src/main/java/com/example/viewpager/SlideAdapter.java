package com.example.viewpager;

import android.content.Context;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.youtube.player.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

//AIzaSyB6i1G83hluSMxm5dBg9nBR8xMZKXx_sjw

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;
    YouTubePlayerView videoView;
    private final YoutubePlayerControl youtubePlayerControl;

    public int[] lst_images = {
            R.drawable.screen1,
            R.drawable.screen2,
            R.drawable.screen3,
            R.drawable.seo1
    };

//    public int[] animations = {
//      R.
//    };

    // list of titles
    public String[] lst_title = {
            "Big Marketing App For Your Small Business.",
            "Chatbot Automatically Replies To All The Queries",
            "Campaigns Your Business\nMarketing tool",
            "Introduction Video"

    };

    // list of descriptions
    public String[] lst_description = {
            "Automate Your Business, " +
                    "Be More Efficient.\n" +
                    "Build Better Relationships, " +
                    "Generate More Business.",
            "Sends relevant messages by asking for alpha numeric inputs",
            "Multi channel sequential messages marketing in single campaign",
            "for more info go to : https://samparksetu.app/"
    };


    public SlideAdapter(Context context) {
        this.context = context;
        youtubePlayerControl = new YoutubePlayerControl(this.context);
    }

    @Override
    public int getCount() {
        Log.e("TAG", "getCount:lst_title--> "+lst_title.length );
        return lst_title.length;
    }

    @Override
    public int getItemPosition(@NonNull @NotNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return (view==(ConstraintLayout)object);
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        ConstraintLayout linearslide = view.findViewById(R.id.slidell);
        ImageView imageView = view.findViewById(R.id.sideimg);
        TextView heading = view.findViewById(R.id.headingview);
        TextView subtitle = view.findViewById(R.id.subtitleview);
        videoView = view.findViewById(R.id.video_view);


        heading.setText(lst_title[position]);
        subtitle.setText(lst_description[position]);
        Log.e("TAG", "instantiateItem: position--: "+position );
        if (position!=3) {
            videoView.setVisibility(View.GONE);
            imageView.setImageResource(lst_images[position]);

        }else {
            imageView.setVisibility(View.INVISIBLE);
            videoView.setVisibility(View.VISIBLE);

            Linkify.addLinks(subtitle, Linkify.ALL);

            youtubePlayerControl.playVideo(videoView, com.example.viewpager.YoutubeConfig.getYoutubeKey());
        }
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((ConstraintLayout)object);
    }

}

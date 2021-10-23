package com.example.viewpager;

import android.content.Context;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerControl implements YouTubePlayer.OnInitializedListener  {

    Context mContext;
    private String youtubeVideoId;

    public YoutubePlayerControl(Context mContext) {
        this.mContext = mContext;
    }

    public void playVideo(YouTubePlayerView youTubePlayerView, String videoId) {
        this.youtubeVideoId = videoId;
        youTubePlayerView.initialize(com.example.viewpager.YoutubeConfig.getApiKey(),this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(youtubeVideoId);
            youTubePlayer.setShowFullscreenButton(false);

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}

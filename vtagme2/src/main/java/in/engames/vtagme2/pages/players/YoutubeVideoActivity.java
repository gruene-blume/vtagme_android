package in.engames.vtagme2.pages.players;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import in.engames.vtagme2.R;
import in.engames.vtagme2.backend.vos.VideoMetaModel;

public class YoutubeVideoActivity extends ActionBarActivity {

    public static String YOUTUBE_API_KEY = "AIzaSyBmlPp_uA1HddVULhDpsLVjX1q7GRqc7Eg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.youtubevideo_activity);

        if (savedInstanceState == null) {

            Intent i = getIntent();
            VideoMetaModel meta = i.getParcelableExtra("meta");

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_container, new PlaceholderFragment(meta))
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.youtube_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private VideoMetaModel meta;
        private YouTubePlayerFragment player;

        public PlaceholderFragment(VideoMetaModel meta) {
            this.meta = meta;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.youtubevideo_fragment, container, false);
            player = rootView.findViewById(R.id.videoplayer);

            player.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener(){
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                }
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                    player.cueVideo(meta.typeid);
                }
            });
            return rootView;
        }
    }

}

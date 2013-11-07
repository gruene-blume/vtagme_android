package in.engames.vtagme2.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import in.engames.vtagme2.BasePageFragment;
import in.engames.vtagme2.R;
import in.engames.vtagme2.backend.VtagClient;
import in.engames.vtagme2.backend.vos.VideoModel;
import in.engames.vtagme2.pages.players.YoutubeVideoActivity;

/**
 * Created by nmannem on 30/10/13.
 */
public class TagPageFragment extends BasePageFragment {
    public static final int ID = 1;

    private String tag;
    private ListView videoListView;

    public static String YOUTUBE_API_KEY = "AIzaSyBmlPp_uA1HddVULhDpsLVjX1q7GRqc7Eg";


    public TagPageFragment() {
        super(ID);
        this.tag = null;
    }
    public TagPageFragment(String tag) {
        super(ID);
        this.tag = tag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tag_page_fragment, container, false);
        videoListView = (ListView) rootView.findViewById(R.id.videoListView);
        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoListAdapter.ViewHolder holder = (VideoListAdapter.ViewHolder) view.getTag();
                if (holder.model != null) {

                    Intent intent = null;
                    if(holder.model.type.equals("youtube")) {
                        intent = new Intent(getActivity(), YoutubeVideoActivity.class);
                    }
                    if (intent != null) {
                        intent.putExtra("meta", holder.model);
                        startActivity(intent);
                    }
                    //Intent intent = YouTubeStandalonePlayer.createVideoIntent(getActivity(), YOUTUBE_API_KEY, holder.model.typeid);
                    //startActivity(intent);
                }
            }
        });
        if (this.tag != null) {
            VtagClient.getInstance().getTagDetails(this.tag, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(JSONObject tagdetails) {
                    try {
                        JSONObject tag = tagdetails.getJSONObject("tag");
                        JSONArray videodetails = tag.getJSONArray("videodetails");

                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<VideoModel>>(){}.getType();
                        List<VideoModel> videoModels = gson.fromJson(videodetails.toString(), listType);

                        videoListView.setAdapter(new VideoListAdapter(getActivity(), R.layout.videocard, videoModels));
                    } catch(JSONException e) {
                    }
                }
                @Override
                public void onFailure(int statusCode, java.lang.Throwable e, org.json.JSONObject errorResponse) {
                    Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    System.out.println(">>>>> <<<< ");
                }
            });
        }
        return rootView;
    }
}

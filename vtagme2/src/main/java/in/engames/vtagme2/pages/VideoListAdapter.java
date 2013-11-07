package in.engames.vtagme2.pages;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.engames.vtagme2.R;
import in.engames.vtagme2.backend.vos.VideoMetaModel;
import in.engames.vtagme2.backend.vos.VideoModel;
import in.engames.vtagme2.tasks.DownloadImageTask;

/**
 * Created by nmannem on 30/10/13.
 */
public class VideoListAdapter extends ArrayAdapter<VideoModel> {
    private final Activity context;
    private final List<VideoModel> objects;

    public VideoListAdapter(Activity context, int textViewResourceId, List<VideoModel> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        VideoMetaModel meta = objects.get(position).video;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(in.engames.vtagme2.R.layout.videocard, null, true);

            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            holder.text = (TextView) convertView.findViewById(R.id.videoTitleView);
            holder.model = meta;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(meta.title);

        Picasso.with(this.getContext()).load(meta.thumb)
                //.placeholder(R.drawable.placeholder)
                //.error(R.drawable.error)
                .resizeDimen(R.dimen.videolist_thumb_width, R.dimen.videolist_thumb_height)
                .centerInside()
                .into(holder.image);
        return convertView;
    }
    public static class ViewHolder {
        ImageView image;
        TextView text;
        VideoMetaModel model;
    }
}

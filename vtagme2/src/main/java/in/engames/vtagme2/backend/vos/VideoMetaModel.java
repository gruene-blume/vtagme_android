package in.engames.vtagme2.backend.vos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nmannem on 30/10/13.
 */
public class VideoMetaModel implements Parcelable {
    public String title;
    public String thumb;
    public String id;
    public String description;

    public int duration;
    public int views;
    public int likes;

    public String typeid;
    public String type;

    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(type);
        out.writeString(typeid);

        out.writeString(title);
        out.writeString(thumb);

        out.writeInt(views);
        out.writeInt(likes);
    }
    public static final Parcelable.Creator<VideoMetaModel> CREATOR
            = new Parcelable.Creator<VideoMetaModel>() {
        public VideoMetaModel createFromParcel(Parcel in) {
            return new VideoMetaModel(in);
        }

        public VideoMetaModel[] newArray(int size) {
            return new VideoMetaModel[size];
        }
    };

    private VideoMetaModel(Parcel in) {
        type = in.readString();
        typeid = in.readString();

        title = in.readString();
        thumb = in.readString();

        views = in.readInt();
        likes = in.readInt();
    }    
}

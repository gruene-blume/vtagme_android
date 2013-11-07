package in.engames.vtagme2.backend;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by nmannem on 30/10/13.
 */
public class VtagClient {
    //private static final String BASE_URL = "http://10.217.134.156:8080/";
    private static final String BASE_URL = "http://192.168.1.146:8080/";

    private AsyncHttpClient client;


    private static VtagClient instance = null;
    /**
     * Returns singleton instance
     */
    public static VtagClient getInstance() {
        if (instance == null) instance = new VtagClient();
        return instance;
    }

    public VtagClient() {
        client  = new AsyncHttpClient();
    }

    private void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d("vtag", ">> getting http data " + getAbsoluteUrl(url));
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }
    private void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d("vtag", ">> posting http data " + getAbsoluteUrl(url));
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


    public void getTagDetails(String tag, AsyncHttpResponseHandler responseHandler) {
        this.get("tag/"+tag, new RequestParams(), responseHandler);
    }
    public void getVideoDetails(String vid, AsyncHttpResponseHandler responseHandler) {
        this.get("video/"+vid, new RequestParams(), responseHandler);
    }
}

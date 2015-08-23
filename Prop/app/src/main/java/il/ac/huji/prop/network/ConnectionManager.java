package il.ac.huji.prop.network;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Android- on 8/20/2015.
 */
public class ConnectionManager {
  private Context mContext;
    private static ConnectionManager _instance;

    public static ConnectionManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new ConnectionManager(context);
        }

        //DataStore.getInstance(context).getBaseURL()+"api/v1/";
        return _instance;
    }

    private ConnectionManager(Context context) {

        mContext=context;
        PropHttpRequest.setContext(mContext);

    }
    private enum Services {
        POST_FACEBOOK
    }

    public void postToFaceBook(String message, String token){
//        String url= "https://graph.facebook.com/me/feed?message=HELLOOOOOOOOOOOOOOO&access_token=CAAJ6UW4HrYkBAP2CN9a5aR33lBs5IzZBKrZArhsJc3ZAQrgZCV41P2U4lAZB1bZAH4LTzrUOFFoWii95UZCwfOxGO7MV0rRk9Qkmgj8h1e35hmgGo8pXz6g5RNQ3yfe3ItqmnffS5T5ZC88orIJJNgvLPFLUHCFwXJOQyqnLKXZAJZBeEnApjfr1oOjyNZBV7UCfZALJewmjGPSQ9iZCNI1Dilnt4ZCMnwPu0dJP9wqWHpmfKBgwZDZD";

        String url= "https://graph.facebook.com/me/feed?message="+message+"&access_token="+token;
        PropHttpRequest req = new PropHttpRequest(url,null,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        req.send();
    }


}

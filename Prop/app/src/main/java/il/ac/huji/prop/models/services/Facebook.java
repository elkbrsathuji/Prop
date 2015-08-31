package il.ac.huji.prop.models.services;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import il.ac.huji.prop.R;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.SocialService;
import il.ac.huji.prop.network.ConnectionManager;

/**
* Created by elkbrs on 16/08/15.
*/
public class Facebook extends SocialService {
    URL url;
//    private static Facebook ourInstance =    new Facebook ("facebook", R.drawable.facebook,false);

//    public Facebook(Context context,String name, int icon, boolean isOpen) {
//        super(context,name, icon, isOpen);
//    }

    public Facebook(Context context,boolean isOpen){
        super(context,"facebook", R.drawable.facebook, isOpen);
    }

//    public static Facebook getInstance() {
//        return ourInstance;
//    }

    public void propagate(Post post) {


        if (post.getPhotFile() != null) {
            publishPostWithPhoto(post);
        } else {
            publishTextPost(post);
        }
    }
    private void  publishTextPost(Post post){
        Bundle params = new Bundle();
        params.putString("message", post.getTxt());

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("DEBUG", "finish upload message");
            /* handle the result */
                    }
                }
        ).executeAsync();
    }
    private void publishPostWithPhoto(Post post){

        Bundle params = new Bundle();
        params.putByteArray("source", uri2BitmapArray(post.getPhotFile()));

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/photos",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("DEBUG", "finish upload");
            /* handle the result */
                    }
                }
        ).executeAsync();


    }



   private  byte[] uri2BitmapArray(Uri uri) {
       ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

       try {
           InputStream iStream = mContext.getContentResolver().openInputStream(uri);
           int bufferSize = 1024;
           byte[] buffer = new byte[bufferSize];

           int len = 0;
           while ((len = iStream.read(buffer)) != -1) {
               byteBuffer.write(buffer, 0, len);
           }
       } catch (Exception e) {
           e.printStackTrace();
           Toast.makeText(mContext, "Photo upload failed", Toast.LENGTH_SHORT).show();
       }
       return byteBuffer.toByteArray();
   }
}

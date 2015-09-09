package il.ac.huji.prop.models.services;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import il.ac.huji.prop.R;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.SocialService;

/**
* Created by elkbrs on 16/08/15.
*/
public class Facebook extends SocialService {
    URL url;


    public Facebook(Context context,boolean isOpen){
        super(context,"facebook", R.drawable.facebook, isOpen);
    }



    public void propagate(Post post,  onFinishUploadListener listener) {
this.listener=listener;

        if (post.getPhotFile() != null) {
//            publishPostWithPhoto(post);
            publishTextWithPic(post);
        } else {
            publishTextPost(post);
        }
    }

    @Override
    public void getLikes(Post post,int i, final onFinishGetLikes listener) {

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+post.getServicePostId(i)+"/likes",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        if (response != null  && response.getJSONObject()!=null) {
                            Log.d("DEBUG", response.getJSONObject().toString());
                            try {
                                JSONArray likesArr=response.getJSONObject().getJSONArray("data");
                                listener.onFinishLikes(likesArr.length());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Log.d("DEBUG","res is null");
                        }
                    }
                }
        ).executeAsync();


    }

    private void  publishTextPost(final Post post){
        Bundle params = new Bundle();
        params.putString("message", post.getTxt());

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("DEBUG", "finish upload message\n  "+response.getJSONObject().toString());
                        String postId="";
                        try {
                            postId=response.getJSONObject().getString("id");
//                       getLikes(post);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }listener.onFinishUpload(postId);                  }
                }
        ).executeAsync();
    }
    private void publishPostWithPhoto(final Post post){

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
                        String postId="";
                        try {
                            postId=response.getJSONObject().getString("post_id");
//                       getLikes(post);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }listener.onFinishUpload(postId);
                    }
                }
        ).executeAsync();


}

    private void publishTextWithPic(final Post post){



        Bundle params = new Bundle();
        params.putByteArray("source", uri2BitmapArray(post.getPhotFile()));
        params.putString("message", post.getTxt());


        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/photos",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("DEBUG", "finish upload");

                        String postId="";
                        try {
                            postId=response.getJSONObject().getString("post_id");
//                       getLikes(post);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }listener.onFinishUpload(postId);            /* handle the result */
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

package il.ac.huji.prop.models.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import il.ac.huji.prop.R;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.Service;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Facebook extends Service{

    private static Facebook ourInstance =    new Facebook ("facebook", R.drawable.facebook,false);

    private Facebook(String name, int icon, boolean isOpen) {
        super(name, icon, isOpen);
    }

    public static Facebook getInstance() {
        return ourInstance;
    }

    public void propagate(Post post){
//        ShareLinkContent content = new ShareLinkContent.Builder()
//                .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                .build();
//        Bitmap image = ...
//        SharePhoto photo = new SharePhoto.Builder()
//                .setBitmap(image)
//                .build();
//        SharePhotoContent content = new SharePhotoContent.Builder()
//                .addPhoto(photo)
//                .build();
//        Uri videoFileUri = ...
//        ShareVideo = new ShareVideo.Builder()
//                .setLocalUrl(videoUrl)
//                .build();
//        ShareVideoContent content = new ShareVideoContent.Builder()
//                .setVideo(video)
//                .build();
        if (post.getPhotFile() != null) {
            byte [] bFile = uri2BitmapArray(post.getPhotFile());
        }

        final String BOUNDERY = "B-!-!-!-!-!-!-!-!-!-!-!-!-Y";
        final String CRLF = "\r\n";
        StringBuilder sbBody_1 = new StringBuilder();
        sbBody_1.append("--" + BOUNDERY + CRLF);
        sbBody_1.append("Content-Disposition: form-data; filename=\"source\"" + CRLF);
        sbBody_1.append(CRLF);
        StringBuilder sbBody_2 = new StringBuilder();
        sbBody_2.append(CRLF + "--" + BOUNDERY + "--");
        URL url = new URL("https://graph.facebook.com/me/photos?access_token=" + accessToken);
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDERY);
            connection.setChunkedStreamingMode(0);
            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            out.write(sbBody_1.toString().getBytes());

            out.write(bFile);// bFile is byte array of the bitmap
            out.write(sbBody_2.toString().getBytes());
            out.close();

            BufferedReader bips = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String temp = null;
            while ((temp = bips.readLine()) != null) {
                Log.d("fbnb", temp);

                bips.close();
                connection.disconnect();
            }
        }
        catch (IOException e){
            Toast.makeText(getApplicationContext(), e.toString()+" FB upload connection failed", Toast.LENGTH_SHORT).show();
        }
//        Bundle params = new Bundle();
//        params.putString("url", "{image-url}");
//        /* make the API call */
//        new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "/me/feed/{photo-id}",
//                params,
//                HttpMethod.POST,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//            /* handle the result */
//                    }
//                }
//        ).executeAsync();
//    GraphRequest request = GraphRequest.newMeRequest(
//            accessToken,
//            new GraphRequest.GraphJSONObjectCallback() {
//                @Override
//                public void onCompleted(
//                        JSONObject object,
//                        GraphResponse response) {
//                    // Application code
//                }
//            });
//    Bundle parameters = new Bundle();
//    parameters.putString("fields", "id,name,link");
//    request.setParameters(parameters);
//    request.executeAsync();
        return;
    }
    byte[] uri2BitmapArray(Uri uri){
        byte[] byteArray = null;
        try
        {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
        }
        catch (Exception e)
        {
            Toast.makeText(this.getApplicationContext(), "Photo upload failed", Toast.LENGTH_SHORT).show();
        }
        return byteArray;
    }
}

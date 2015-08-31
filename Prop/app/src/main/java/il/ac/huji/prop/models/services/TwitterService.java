package il.ac.huji.prop.models.services;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.twitter.sdk.android.core.AuthToken;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.File;

import il.ac.huji.prop.MainActivity;
import il.ac.huji.prop.R;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.SocialService;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by elkbrs on 16/08/15.
 */
public class TwitterService extends SocialService {
    public  static String TWITTER_CONSUMER_KEY = "yeMH3gwr7y0T7cnZybOIeRjr2";
    public static String TWITTER_CONSUMER_SECRET = "WD8X9xOb2TU1hsovhZpigPszzg44rB1r8KaOiT1xRZm1UqQwkP";

private AsyncTask<String, String, String> propTask;
private static TwitterAuthToken token;
    private static String tokenSecret;

    public TwitterService(Context context,boolean isOpen){
        super(context,"twitter", R.drawable.twitter, isOpen);
    }


    @Override
    public void propagate(final Post post) {
propTask=new AsyncTask<String, String, String>() {
    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        pDialog = new ProgressDialog(MainActivity.this);
//        pDialog.setMessage("Updating to twitter...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        pDialog.show();
    }

    /**
     * getting Places JSON
     * */
    protected String doInBackground(String... args) {
        Log.d("Tweet Text", "> " + args[0]);
        String msg = args[0];
        try {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
            builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);

            // Access Token
//            String access_token = mSharedPreferences.getString(PREF_KEY_OAUTH_TOKEN, "");
            // Access Token Secret
//            String access_token_secret = mSharedPreferences.getString(PREF_KEY_OAUTH_SECRET, "");

            AccessToken accessToken = new AccessToken(token.token, token.secret);
            Twitter twitter = new TwitterFactory(builder.build()).getInstance(accessToken);



            StatusUpdate status = new StatusUpdate(msg);



            if (post.getPhotFile()!=null){
                status.setMedia(new File(getRealPathFromURI(mContext,post.getPhotFile())));
            }
            // Update status
            twitter4j.Status response = twitter.updateStatus(status);

            Log.d("Status", "> " + response.getText());
        } catch (TwitterException e) {
            // Error in updating status
            Log.d("Twitter Update Error", e.getMessage());
        } catch (twitter4j.TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * After completing background task Dismiss the progress dialog and show
     * the data in UI Always use runOnUiThread(new Runnable()) to update UI
     * from background thread, otherwise you will get error
     * **/
    protected void onPostExecute(String file_url) {
        // dismiss the dialog after getting all products
//        pDialog.dismiss();
//        // updating UI from Background Thread
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(getApplicationContext(),
//                        "Status tweeted successfully", Toast.LENGTH_SHORT)
//                        .show();
//                // Clearing EditText field
//                txtUpdate.setText("");
//            }
//        });
    }

};



propTask.execute(post.getTxt());


    }
    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

//    public void uploadPic(File file, String message,Twitter twitter) throws Exception  {
//        try{
//            StatusUpdate status = new StatusUpdate(message);
//            status.setMedia(file);
//            twitter.updateStatus(status);}
//        catch(TwitterException e){
//            Log.d("TAG", "Pic Upload error" + e.getErrorMessage());
//            throw e;
//        }
//    }

    public static void setToken(TwitterAuthToken token) {
    TwitterService.token=token;
    }
}

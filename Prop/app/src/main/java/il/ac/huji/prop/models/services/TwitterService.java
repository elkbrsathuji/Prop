package il.ac.huji.prop.models.services;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;

import java.io.File;

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
//     twitter;
    public TwitterService(Context context,boolean isOpen){
        super(context,"twitter", R.drawable.twitter, isOpen);

    }


    @Override
    public void propagate(final Post post, onFinishUploadListener listener) {
        this.listener=listener;
propTask=new AsyncTask<String, String, String>() {
    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    /**
     * getting Places JSON
     * */
    protected String doInBackground(String... args) {
        Log.d("Tweet Text", "> " + args[0]);
        String msg = args[0];
        twitter4j.Status response=null;
        try {


            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
            builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);


            AccessToken accessToken = new AccessToken(token.token, token.secret);
            Twitter   twitter = new TwitterFactory(builder.build()).getInstance(accessToken);

            StatusUpdate status = new StatusUpdate(msg);



            if (post.getPhotFile()!=null){
                status.setMedia(new File(getRealPathFromURI(mContext,post.getPhotFile())));
            }
            // Update status
          response  = twitter.updateStatus(status);

            Log.d("Status", "> " + response.getText());
        } catch (TwitterException e) {
            // Error in updating status
            Log.d("Twitter Update Error", e.getMessage());
        } catch (twitter4j.TwitterException e) {
            e.printStackTrace();
        }
        return String.valueOf(response.getId());
    }

    /**
     * After completing background task Dismiss the progress dialog and show
     * the data in UI Always use runOnUiThread(new Runnable()) to update UI
     * from background thread, otherwise you will get error
     * **/
    protected void onPostExecute(String id) {
        TwitterService.this.listener.onFinishUpload(id);
    }

};



propTask.execute(post.getTxt());


    }

    @Override
    public void getLikes(final Post post, final int i,final onFinishGetLikes listener) {


        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                try {
                    ConfigurationBuilder builder = new ConfigurationBuilder();
                    builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
                    builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);


                    AccessToken accessToken = new AccessToken(token.token, token.secret);
                    Twitter   twitter = new TwitterFactory(builder.build()).getInstance(accessToken);
                    return twitter.tweets().getRetweets(Long.valueOf(post.getServicePostId(i))).size();
                } catch (twitter4j.TwitterException e) {
                    e.printStackTrace();
                    return 0;

                }

            }

            @Override
            protected void onPostExecute(Integer integer) {
                listener.onFinishLikes(integer);
                super.onPostExecute(integer);
            }
        }.execute();

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


    public static void setToken(TwitterAuthToken token) {
    TwitterService.token=token;
    }
}

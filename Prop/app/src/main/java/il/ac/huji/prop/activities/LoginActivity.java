package il.ac.huji.prop.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import il.ac.huji.prop.models.services.TwitterService;
import io.fabric.sdk.android.Fabric;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import il.ac.huji.prop.R;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class LoginActivity extends ActionBarActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.


private LoginButton fbLogin;
    private TwitterLoginButton twitLogin;
    CallbackManager clbkManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TwitterService.TWITTER_CONSUMER_KEY, TwitterService.TWITTER_CONSUMER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);






//        Log.d("DEBUG","hash key:  "+FacebookSdk.getApplicationSignature(getApplicationContext()));
        clbkManager=CallbackManager.Factory.create();
        fbLogin=(LoginButton)findViewById(R.id.login_button);
        fbLogin.setPublishPermissions(Arrays.asList("publish_actions"));
        fbLogin.registerCallback(clbkManager,new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("DEBUG","success login");

                AccessToken fbToken=loginResult.getAccessToken();
                Log.d("DEBUG",fbToken.getToken());
//                publish(fbToken);
//
                Intent i=new Intent(LoginActivity.this,PostActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {
                Log.d("DEBUG","cancel login");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d("DEBUG","error login "+e.toString() );
            }
        });


        twitLogin = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        twitLogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
Log.d("DEBUG","success to login to twitter");
                TwitterService.setToken( result.data.getAuthToken());
                Intent i=new Intent(LoginActivity.this,PostActivity.class);
                startActivity(i);
            }

            @Override
            public void failure(TwitterException exception) {
               Log.d("DEBUG","fail to login to twitter. "+exception.getMessage());
            }
        });
    }


//    private void loginToTwitter() {
//        // Check if already logged in
//        if (!isTwitterLoggedInAlready()) {
//            ConfigurationBuilder builder = new ConfigurationBuilder();
//            builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
//            builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
//            Configuration configuration = builder.build();
//
//            TwitterFactory factory = new TwitterFactory(configuration);
//            twitter = factory.getInstance();
//
//            try {
//                requestToken = twitter
//                        .getOAuthRequestToken(TWITTER_CALLBACK_URL);
//                this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
//                        .parse(requestToken.getAuthenticationURL())));
//            } catch (TwitterException e) {
//                e.printStackTrace();
//            }
//        } else {
//            // user already logged into twitter
//            Toast.makeText(getApplicationContext(),
//                    "Already Logged into twitter", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    /**
//     * Check user already logged in your application using twitter Login flag is
//     * fetched from Shared Preferences
//     * */
//    private boolean isTwitterLoggedInAlready() {
//        // return twitter login status from Shared Preferences
//        return mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
//    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        clbkManager.onActivityResult(requestCode, resultCode, data);
        twitLogin.onActivityResult(requestCode, resultCode, data);
    }


}

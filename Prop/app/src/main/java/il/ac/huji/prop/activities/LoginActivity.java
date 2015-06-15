package il.ac.huji.prop.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import il.ac.huji.prop.R;

public class LoginActivity extends ActionBarActivity {
private LoginButton fbLogin;
    CallbackManager clbkManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        clbkManager=CallbackManager.Factory.create();
        fbLogin=(LoginButton)findViewById(R.id.login_button);
        fbLogin.setPublishPermissions(Arrays.asList("publish_actions"));
        fbLogin.registerCallback(clbkManager,new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken fbToken=loginResult.getAccessToken();
                Log.d("DEBUG",fbToken.getToken());
                publish(fbToken);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

    }


    private void publish(AccessToken fbToken){
        GraphRequest request = GraphRequest.newMeRequest(fbToken,new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                String id = null;
                try {
                    id = graphResponse.getJSONObject().getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("DEBUG",id);
            }
        });
        Bundle params= new Bundle();
        params.putString("fields","id,name,link");
        request.setParameters(params);
        request.executeAsync();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

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
}

package il.ac.huji.prop.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.*;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Android- on 8/20/2015.
 */
public class PropHttpRequest extends Request<String> {





        private HashMap<String, String> params;
        private Listener<String> listener;
        private Response.ErrorListener errorListener;
        private RequestQueue queue;
        private static final String TAG = "NETWORK";
        private String service;
        private String url;

        private static Context mContext;
        private static String token;

        public PropHttpRequest( String url,final HashMap<String, String> params,  Listener<String> listener,
                               ErrorListener errorListener) {



            super(Method.POST, url, errorListener);
            this.params=params;

            this.listener=listener;
            this.errorListener=errorListener;
            try{
                this.queue = Volley.newRequestQueue(mContext.getApplicationContext());
            }catch(OutOfMemoryError e){

            }
            this.url=url;
            setService(url);
        }

        private void setService(String url){
            URI uri;
            try {
                uri = new URI(url);

                String path = uri.getPath();
                service = path.substring(path.lastIndexOf('/') + 1);

            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String parsed;
            try {
                parsed = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
            } catch (Exception e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed,
                    HttpHeaderParser.parseCacheHeaders(response));
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return params;
        }
        @Override
        protected void deliverResponse(String response) {
            Log.i(TAG, service+":\t"+response.toString());


            if(listener != null){

                listener.onResponse(response);
            }


        }

        @Override
        public void deliverError(VolleyError error) {
            Log.e(TAG, "ERROR: "+service+" "+(error != null && error.networkResponse != null?error.getClass().getSimpleName()+": "+error.networkResponse.statusCode+" "+new String(error.networkResponse.data):"ERROR"));
            //Talya - check if it's makes sense



            if(errorListener != null){
                errorListener.onErrorResponse(error);
            }



            //		super.deliverError(error);
        }


        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            //       headers.put("ApplicationType", "3");
            //       headers.put("Accept-Language", Locale.getDefault().getLanguage());

            return headers;
        }

        public void send(){
            Log.d(TAG, service + "\nURL: " + url + "\nPARAMS: " + params);
            if (queue!=null){
                queue.add(this);
            }else{
                deliverError(null);
            }
        }

        public static void setToken(String userToken){
            token=userToken;
        }

        public static void setContext(Context context){
            mContext=context;
        }




}

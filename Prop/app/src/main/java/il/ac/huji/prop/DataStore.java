package il.ac.huji.prop;

import android.content.Context;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;

import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.SocialService;
import il.ac.huji.prop.models.services.Facebook;
import il.ac.huji.prop.models.services.TwitterService;

/**
 * Created by Android- on 8/30/2015.
 */
public class DataStore {

    private ArrayList<SocialService> mServices;
    private Context mContext;
    private static DataStore _instance;
    private DataStore(Context context){
        mContext=context;
        initServices();
    }

    public static DataStore getInstance(Context context){
        if (_instance==null){
            _instance= new DataStore(context);
        }

        return _instance;
    }

    private void initServices(){
        mServices= new ArrayList<SocialService>();
        mServices.add(new Facebook(mContext,true));
        mServices.add(new TwitterService(mContext,true));
    }

    public ArrayList<SocialService>getServicesList(){
        return mServices;
    }

    public HashMap<Integer,SocialService> getServicesListAsHash(){
        HashMap<Integer,SocialService> mHash= new HashMap<Integer,SocialService>();
        for (SocialService sc:mServices){
            mHash.put(sc.getId(),sc);
        }
        return mHash;
    }
}

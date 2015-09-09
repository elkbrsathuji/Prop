package il.ac.huji.prop.models.ManagerModels;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import il.ac.huji.prop.DataStore;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.PropList;
import il.ac.huji.prop.models.SocialService;

/**
 * Created by elkbrs on 27/05/15.
 */
public class PropManager {

    private static PropManager _instance;
private Context mContext;
    private PropManager(Context context) {
mContext=context;

    }

  public static PropManager getInstance(Context context){
      if(_instance==null){
          _instance=new PropManager(context);
      }
      return _instance;
  }

    public void propagate(final Post post, PropList props, final SocialService.onFinishUploadListener listener)  {

        for ( int counter=0;counter<props.getServices().length;counter++){
            int i= props.getServices()[counter];
            Log.d("DEBUG","enter with service: "+i);
            final SocialService sn=DataStore.getInstance(mContext).getServicesListAsHash().get(i);
            if (sn.isOpen()) {
                final int serviceId=i;
                sn.propagate(post, new SocialService.onFinishUploadListener() {
                    @Override
                    public void onFinishUpload(String id) {
Log.d("DEBUG","add service: "+serviceId);
                        post.addPostId(serviceId,id);
                        listener.onFinishUpload(id);

                    }
                });
            }
        }

//        for (SocialService sn: props.getServiceList()){
//            if (sn.isOpen()) {
//                sn.propagate(post);
//            }
//        }
    }
}

package il.ac.huji.prop.models.ManagerModels;

import android.content.Context;

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

    public void propagate(Post post, PropList props)  {

        for (int i: props.getServices()){
            SocialService sn=DataStore.getInstance(mContext).getServicesListAsHash().get(i);
            if (sn.isOpen()) {
                sn.propagate(post);
            }
        }

//        for (SocialService sn: props.getServiceList()){
//            if (sn.isOpen()) {
//                sn.propagate(post);
//            }
//        }
    }
}

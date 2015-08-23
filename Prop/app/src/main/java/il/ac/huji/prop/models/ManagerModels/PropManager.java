package il.ac.huji.prop.models.ManagerModels;

import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.PropList;
import il.ac.huji.prop.models.SocialService;

/**
 * Created by elkbrs on 27/05/15.
 */
public class PropManager {

    private static PropManager _instance;

    private PropManager( ) {


    }

  public static PropManager getInstance(){
      if(_instance==null){
          _instance=new PropManager();
      }
      return _instance;
  }

    public void propagate(Post post, PropList props)  {
        for (SocialService sn: props.getServiceList()){
            if (sn.isOpen()) {
                sn.propagate(post);
            }
        }
    }
}

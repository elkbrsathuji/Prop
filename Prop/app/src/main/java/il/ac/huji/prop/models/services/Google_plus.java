package il.ac.huji.prop.models.services;

import android.content.Context;

import il.ac.huji.prop.R;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.SocialService;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Google_plus extends SocialService {
    public Google_plus(Context context,boolean isOpen){
        super(context,"twitter", R.drawable.g_plus, isOpen);
    }

    @Override
    public void propagate(Post post, onFinishUploadListener listener) {

    }
}

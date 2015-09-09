package il.ac.huji.prop.models.services;

import android.content.Context;

import il.ac.huji.prop.R;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.SocialService;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Linkedin extends SocialService{
    public Linkedin(Context context,boolean isOpen){
        super(context,"twitter", R.drawable.linkedin, isOpen);
    }

    @Override
    public void propagate(Post post,  onFinishUploadListener listener) {

    }

    @Override
    public void getLikes(Post post, int i,onFinishGetLikes listener) {

    }
}

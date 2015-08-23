package il.ac.huji.prop.models;

import android.content.Context;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by Android- on 5/10/2015.
 */
public abstract class SocialService {

    private String name;

    private int icon;
    private boolean isOpen;
    protected Context mContext;

    public SocialService(Context context, String name, int icon, boolean isOpen){
        this.name=name;
        this.icon=icon;
        this.isOpen = isOpen;
        this.mContext=context;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isOpen(){
        return this.isOpen;
    }
    public void setOpen(){

    }

    public void propagate(Post post){

        return;
    }
}

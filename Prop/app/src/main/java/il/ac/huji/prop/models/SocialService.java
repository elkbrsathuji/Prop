package il.ac.huji.prop.models;

import android.content.Context;
import android.net.Uri;

import com.google.gson.annotations.Expose;

import java.io.IOException;

/**
 * Created by Android- on 5/10/2015.
 */
public abstract class SocialService {

    private static int counter=0;
    private String name;

    private int icon;

    private boolean isOpen;

    private int id;

    protected Context mContext;

    public SocialService(Context context, String name, int icon, boolean isOpen){
        this.name=name;
        this.icon=icon;
        this.isOpen = isOpen;
        this.mContext=context;
        this.id=counter++;
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

    public int getId(){
        return this.id;
    }

    public abstract void propagate(Post post);

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof  SocialService)){
            return false;
        }
        return this.getName().equals(((SocialService)o).getName());
    }
}

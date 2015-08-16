package il.ac.huji.prop.models;

import android.net.Uri;

/**
 * Created by elkbrs on 16/08/15.
 */
public class User {
    String name;
    Uri userPic;

    public User(String name) {
        this.name = name;
        this.userPic = null;
    }

    public User(String name, Uri userPic) {
        this.name = name;
        this.userPic = userPic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserPic(Uri userPic) {
        this.userPic = userPic;
    }

    public String getName() {
        return name;
    }

    public Uri getUserPic() {
        return userPic;
    }
}

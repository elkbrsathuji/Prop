package il.ac.huji.prop.models;

import java.util.ArrayList;

/**
 * Created by elkbrs on 16/08/15.
 */
public class History {
    private static History ourInstance = new History();
    ArrayList <Prop> propList;
    ArrayList <Post> postList;
    User mUser;
    public static History getInstance() {
        return ourInstance;
    }

    private History() {
        propList = new ArrayList<Prop>();
        postList = new ArrayList<Post>();
        mUser = null;
    }

    public ArrayList<Prop> getPropList() {
        return propList;
    }

    public void setPropList(ArrayList<Prop> propList) {
        this.propList = propList;
    }
    public ArrayList<Post> getPostList() {
        return postList;
    }

    public void setPostList(ArrayList<Post> postList) {
        this.postList = postList;
    }

    public void addPost (Post p){
        postList.add(p);
    }

    public User getUser() {
        return mUser;
    }

}

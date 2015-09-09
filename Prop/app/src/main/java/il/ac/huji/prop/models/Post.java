package il.ac.huji.prop.models;

import android.content.Context;
import android.location.Location;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;

import il.ac.huji.prop.DataStore;

/**
 * Created by elkbrs on 19/07/15.
 */
public class Post {
    private Uri photFile;
    private Uri vidFile;
    private Uri uplFile;
    private String txt;
    private Location loc;
private PropList mProp;
    private HashMap<Integer,String> postIds;

    public PropList getmProp() {
        return mProp;
    }

    public void setmProp(PropList mProp) {
        this.mProp = mProp;
    }

    String id;
    private int likesCounter;
    private int twitCounter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public Post(){

        this.photFile = null;
        this.vidFile = null;
        this.uplFile = null;
        this.txt = null;
        this.loc = null;
        postIds= new HashMap<Integer,String>();
    }

    public Post(Uri photFile, Uri vidFile, Uri uplFile, String txt, Location loc) {

        this.photFile = photFile;
        this.vidFile = vidFile;
        this.uplFile = uplFile;
        this.txt = txt;
        this.loc = loc;
        postIds= new HashMap<Integer,String>();
    }

    public Uri getPhotFile() {
        return photFile;
    }

    public void setPhotFile(Uri photFile) {
        this.photFile = photFile;
    }

    public Uri getVidFile() {
        return vidFile;
    }

    public void setVidFile(Uri vidFile) {
        this.vidFile = vidFile;
    }

    public Uri getUplFile() {
        return uplFile;
    }

    public void setUplFile(Uri uplFile) {
        this.uplFile = uplFile;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public void addPostId(int service, String id){
        postIds.put(service,id);
    }

    public String getServicePostId(int  service){

        return postIds.get(service);
    }
}

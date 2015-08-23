package il.ac.huji.prop.models;

import java.util.ArrayList;

/**
 * Created by elkbrs on 27/05/15.
 */
public class PropList {

    private ArrayList<SocialService> serviceList;
    private String name;
    private long id;


    public PropList(String name, ArrayList<SocialService> snList, long id) {

        this.serviceList = snList;
        this.name = name;
        this.id = id;
    }

    public void setServiceList(ArrayList<SocialService> serviceList) {
        this.serviceList = serviceList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SocialService> getServiceList() {
        return serviceList;
    }

    public String toString() {
        return name;
    }

    public long getId() {
        return id;
    }

}

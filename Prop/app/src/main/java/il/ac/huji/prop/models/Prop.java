package il.ac.huji.prop.models;

import java.util.ArrayList;

/**
 * Created by elkbrs on 27/05/15.
 */
public class Prop {

    private ArrayList<Service> serviceList;
    private String name;
    private long id;


    public Prop(String name, ArrayList<Service> snList,  long id) {

        this.serviceList = snList;
        this.name = name;
        this.id = id;
    }

    public void setServiceList(ArrayList<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Service> getServiceList() {
        return serviceList;
    }

    public String toString() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void propagate(Post post){
        for (Service sn: serviceList){
            if (sn.isOpen()) {
                sn.propagate(post);
            }
        }
    }
}

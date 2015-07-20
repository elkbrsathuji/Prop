package il.ac.huji.prop.models;

import java.util.ArrayList;

/**
 * Created by elkbrs on 27/05/15.
 */
public class Prop {

    private ArrayList<Service> snList;
    private String name;
    private long id;


    public Prop(String name, ArrayList<Service> snList,  long id) {

        this.snList = snList;
        this.name = name;
        this.id = id;
    }

    public void setSnList(ArrayList<Service> snList) {
        this.snList = snList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Service> getSnList() {
        return snList;
    }

    public String toString() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void propagate(Post post){
        for (Service sn:snList){
            if (sn.isOpen()) {
                sn.propagate(post);
            }
        }
    }
}

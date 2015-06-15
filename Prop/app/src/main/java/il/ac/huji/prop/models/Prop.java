package il.ac.huji.prop.models;

/**
 * Created by elkbrs on 27/05/15.
 */
public class Prop {

    private SocialNetwork [] snList;
    private String name;
    private long id;


    public Prop(SocialNetwork[] snList, String name, long id) {

        this.snList = snList;
        this.name = name;
        this.id = id;
    }

    public void setSnList(SocialNetwork[] snList) {
        this.snList = snList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SocialNetwork [] getSnList() {
        return snList;
    }

    public String toString() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void Propagate(){
        for (SocialNetwork sn:snList){
            if (sn.isOpen()) {
                sn.propagate();
            }
        }
    }
}

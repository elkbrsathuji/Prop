package il.ac.huji.prop.models;

/**
 * Created by Android- on 5/10/2015.
 */
public class SocialNetwork {

    private String name;
    private int icon;

    public SocialNetwork(String name, int icon){
        this.name=name;
        this.icon=icon;
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
}

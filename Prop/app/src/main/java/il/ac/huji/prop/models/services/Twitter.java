package il.ac.huji.prop.models.services;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Twitter {
    private static Twitter ourInstance = new Twitter();

    public static Twitter getInstance() {
        return ourInstance;
    }

    private Twitter() {
    }
}

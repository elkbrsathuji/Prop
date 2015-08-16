package il.ac.huji.prop.models.services;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Facebook {
    private static Facebook ourInstance = new Facebook();

    public static Facebook getInstance() {
        return ourInstance;
    }

    private Facebook() {
    }
}

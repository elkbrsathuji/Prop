package il.ac.huji.prop.models.services;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Linkedin {
    private static Linkedin ourInstance = new Linkedin();

    public static Linkedin getInstance() {
        return ourInstance;
    }

    private Linkedin() {
    }
}

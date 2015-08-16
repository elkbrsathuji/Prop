package il.ac.huji.prop.models.services;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Vimeo {
    private static Vimeo ourInstance = new Vimeo();

    public static Vimeo getInstance() {
        return ourInstance;
    }

    private Vimeo() {
    }
}

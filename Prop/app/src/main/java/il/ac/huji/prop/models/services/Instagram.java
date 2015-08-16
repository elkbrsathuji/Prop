package il.ac.huji.prop.models.services;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Instagram {
    private static Instagram ourInstance = new Instagram();

    public static Instagram getInstance() {
        return ourInstance;
    }

    private Instagram() {
    }
}

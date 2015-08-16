package il.ac.huji.prop.models.services;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Pinterest {
    private static Pinterest ourInstance = new Pinterest();

    public static Pinterest getInstance() {
        return ourInstance;
    }

    private Pinterest() {
    }
}

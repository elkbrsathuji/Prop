package il.ac.huji.prop.models.services;

/**
 * Created by elkbrs on 16/08/15.
 */
public class Google_plus {
    private static Google_plus ourInstance = new Google_plus();

    public static Google_plus getInstance() {
        return ourInstance;
    }

    private Google_plus() {
    }
}

//package il.ac.huji.prop.models.services;
//
//import il.ac.huji.prop.models.Post;
//import il.ac.huji.prop.models.SocialService;
//
///**
// * Created by elkbrs on 31/08/15.
// */
//public class DropboxService extends SocialService {
////    https://www.dropbox.com/developers/core/start/android
//
//    private  static String DROPBOX_CONSUMER_KEY = "tuvm4wau0iug0a0";
//    private static String DROPBOX_CONSUMER_SECRET = "njzx0os5zr7e37l";
//    private Post mPost;
//    // In the class declaration section:
//    private DropboxAPI<AndroidAuthSession> mDBApi;
//
//
//
//    public DropboxService() {
//        mPost = null;
//        initApi();
//    }
//    public DropboxService(Post p){
//        mPost = p;
//        initApi();
//    }
//
//    public Post getPost() {
//        return mPost;
//    }
//
//    public void setPost(Post p) {
//        this.mPost = p;
//    }
//    private void initApi(){
//        // And later in some initialization function:
//        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
//        AndroidAuthSession session = new AndroidAuthSession(appKeys);
//        mDBApi = new DropboxAPI<AndroidAuthSession>(session);
//
//    }
//
//
//}

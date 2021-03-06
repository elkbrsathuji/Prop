package il.ac.huji.prop.models.ManagerModels;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import il.ac.huji.prop.Utils.UriDeserializer;
import il.ac.huji.prop.Utils.UriSerializer;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.PropList;
import il.ac.huji.prop.models.SocialService;

/**
 * Created by Android- on 8/30/2015.
 */
public class SharedPrefManager {
    private static final String PREFS_NAME = "PROP_APP";
    private static final String ALL_SERVICES = "ALL_SERVICES";
    private static final String ALL_PROPS = "ALL_PROPS";
    private static final String HISTORY = "HISTORY";
//    private static final String PREFS_NAME = "PROP_APP";

//    public static ArrayList<String> getAllServices(Context context){
//return
//    }



    public static ArrayList<String> getPropServices(Context context, String propName){
    return getList(context, propName);
}
    public static ArrayList<PropList> getListOfProps(Context context){
        SharedPreferences settings;
        List<PropList> list;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(ALL_PROPS)) {
            String jsonFavorites = settings.getString(ALL_PROPS, null);
            Gson gson = new Gson();
            PropList[] strings = gson.fromJson(jsonFavorites,
                    PropList[].class);

            list = Arrays.asList(strings);
            list = new ArrayList<PropList>(list);
        } else
            return null;

        PropList.counter=list.size();
        return (ArrayList<PropList>) list;
    }

    public static void addProp(Context context, PropList prop) {
        ArrayList<PropList> list = getListOfProps(context);
        if (list == null) {
            list = new ArrayList<PropList>();
            list.add(prop);
        }
        else if (list.contains(prop)){
           list.get( list.indexOf(prop)).setName(prop.getName());
            list.get( list.indexOf(prop)).setServices(prop.getServices());
        }else{
            list.add(prop);
        }

        savePropList(context, list);
    }

    private static  void savePropList(Context context, ArrayList<PropList> list) {
        SharedPreferences settings;


        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
//        Type listOfTestObject = new TypeToken<List<PropList>>(){}.getType();
        String jsonList = gson.toJson(list,ArrayList.class);

        editor.putString(ALL_PROPS, jsonList);

        editor.commit();
    }

    public static  void saveHistory(Context context, ArrayList<Post> list) {
        SharedPreferences settings;


        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();



        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Uri.class, new UriSerializer())
                .create();

        String jsonList =gson.toJson(list, ArrayList.class);
//        bdl.putString("oModel",);



//        Gson gson = new Gson();
////        Type listOfTestObject = new TypeToken<List<PropList>>(){}.getType();
//        String jsonList = gson.toJson(list,ArrayList.class);

        editor.putString(HISTORY, jsonList);

        editor.commit();
    }

    public static ArrayList<Post> getHistory(Context context){
        SharedPreferences settings;
        List<Post> list;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(HISTORY)) {
            String jsonFavorites = settings.getString(HISTORY, null);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Uri.class, new UriDeserializer())
                    .create();

            Post[] strings = gson.fromJson(jsonFavorites,   Post[].class);

//
//            Gson gson = new Gson();
//            Post[] strings = gson.fromJson(jsonFavorites,
//                    Post[].class);

            list = Arrays.asList(strings);
            list = new ArrayList<Post>(list);
        } else
            return null;

        PropList.counter=list.size();
        return (ArrayList<Post>) list;
    }

    // This four methods are used for maintaining favorites.
    private static  void saveList(Context context, List<String> list, String name) {
        SharedPreferences settings;


       SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonList = gson.toJson(list);

        editor.putString(name, jsonList);

        editor.commit();
    }

    private static void addFavorite(Context context, String str, String name) {
        List<String> list = getList(context,name);
        if (list == null) {
            list = new ArrayList<String>();
        }
        list.add(str);
        saveList(context, list,name);
    }

    private static void removeFavorite(Context context, String str, String name) {
        ArrayList<String> list = getList(context,name);
        if (list != null) {
            list.remove(str);
            saveList(context, list,name);
        }
    }

    private static ArrayList<String> getList(Context context,String name) {
        SharedPreferences settings;
        List<String> list;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(name)) {
            String jsonFavorites = settings.getString(name, null);
            Gson gson = new Gson();
            String[] strings = gson.fromJson(jsonFavorites,
                    String[].class);

            list = Arrays.asList(strings);
            list = new ArrayList<String>(list);
        } else
            return null;

        return (ArrayList<String>) list;

}





}


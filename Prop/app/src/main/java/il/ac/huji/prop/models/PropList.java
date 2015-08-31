package il.ac.huji.prop.models;

import java.util.ArrayList;

/**
 * Created by elkbrs on 27/05/15.
 */
public class PropList {

    public static int counter;
//    private ArrayList<SocialService> serviceList;
    int[] services;
    private String name;
    private int id;



//    public PropList(String name, ArrayList<SocialService> snList) {
//
//        this.serviceList = snList;
//        this.name = name;
//    }
//
//    public void setServiceList(ArrayList<SocialService> serviceList) {
//        this.serviceList = serviceList;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public ArrayList<SocialService> getServiceList() {
//        return serviceList;
//    }
//
//    public ArrayList<String> getServiceListAsString() {
//        ArrayList<String> services=new ArrayList<String>();
//        for (SocialService ser:serviceList){
//            services.add(ser.getName());
//        }
//        return services;
//    }


    public PropList(int[] services, String name) {
        this.services = services;
        this.name = name;
        this.id=counter++;
    }

    public PropList() {

    }

    public int[] getServices() {
        return services;
    }

    public void setServices(int[] services) {
        this.services = services;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }


    public String getName(){
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof  PropList)){
            return false;
        }
        return id==((PropList)o).getId();
    }
}

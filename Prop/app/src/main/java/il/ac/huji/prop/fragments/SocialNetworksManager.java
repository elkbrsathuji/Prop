package il.ac.huji.prop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import il.ac.huji.prop.R;
import il.ac.huji.prop.adapters.SocialNetworkOptionsAdapter;
import il.ac.huji.prop.models.SocialNetwork;

/**
 * Created by Android- on 5/10/2015.
 */
public class SocialNetworksManager extends BaseFragment {

    private View mView;
    private ListView networkOptionsList;
    private ListView propList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.social_network_manager,null);
        networkOptionsList=(ListView)mView.findViewById(R.id.social_network_options);
        propList=(ListView)mView.findViewById(R.id.social_network_groups);
        initOptionsList();
        return mView;
    }

    private void initOptionsList(){
        /*TODO create a "blank" prop that contains all the social nets, by getting Prop.snList (social network list)
        * we can do all this automatically
        */
        ArrayList<SocialNetwork> networks=new ArrayList<SocialNetwork>();
        networks.add(new SocialNetwork("facebook", R.drawable.facebook));
        networks.add(new SocialNetwork("g+", R.drawable.g_plus));
        networks.add(new SocialNetwork("instagram", R.drawable.instagram));
        networks.add(new SocialNetwork("linkedIn", R.drawable.linkedin));

        SocialNetworkOptionsAdapter adapter= new SocialNetworkOptionsAdapter(getActivity(),networks);
        networkOptionsList.setAdapter(adapter);
    }
    private void initPropList(){
        
    }
}

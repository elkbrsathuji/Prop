package il.ac.huji.prop.fragments;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import il.ac.huji.prop.R;
import il.ac.huji.prop.adapters.GroupAdapter;
import il.ac.huji.prop.adapters.SocialNetworkOptionsAdapter;
import il.ac.huji.prop.models.SocialNetwork;

/**
 * Created by Android- on 5/10/2015.
 */
public class SocialNetworksManager extends BaseFragment {

    private View mView;
    private ListView networkOptionsList;
    private ListView propList;
    ImageView first,second;
    GroupAdapter gadapter;
    RelativeLayout target;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.social_network_manager,null);
        networkOptionsList=(ListView)mView.findViewById(R.id.social_network_options);
        propList=(ListView)mView.findViewById(R.id.social_network_groups);
target=(RelativeLayout)mView.findViewById(R.id.droppedTarget);
        target.setOnDragListener(new MyDragEventListener());
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


      gadapter  = new GroupAdapter(getActivity());
        propList.setAdapter(gadapter);
    }


    protected class MyDragEventListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            final int action = event.getAction();

            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //All involved view accept ACTION_DRAG_STARTED for MIMETYPE_TEXT_PLAIN
                    if (event.getClipDescription()
                            .hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
                    {
//                        commentMsg += v.getTag()
//                                + " : ACTION_DRAG_STARTED accepted.\n";
//                        comments.setText(commentMsg);
                        return true; //Accept
                    }else{
//                        commentMsg += v.getTag()
//                                + " : ACTION_DRAG_STARTED rejected.\n";
//                        comments.setText(commentMsg);
                        return false; //reject
                    }
                case DragEvent.ACTION_DRAG_ENTERED:
//                    commentMsg += v.getTag() + " : ACTION_DRAG_ENTERED.\n";
//                    comments.setText(commentMsg);
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
//                    commentMsg += v.getTag() + " : ACTION_DRAG_LOCATION - "
//                            + event.getX() + " : " + event.getY() + "\n";
//                    comments.setText(commentMsg);
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
//                    commentMsg += v.getTag() + " : ACTION_DRAG_EXITED.\n";
//                    comments.setText(commentMsg);
                    return true;
                case DragEvent.ACTION_DROP:
                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);

//                    commentMsg += v.getTag() + " : ACTION_DROP" + "\n";
//                    comments.setText(commentMsg);

                    //If apply only if drop on buttonTarget
                    if(v == target){
                        int droppedItem = Integer.parseInt(item.getText().toString());

//                        commentMsg += "Dropped item - "
//                                + droppedItem + "\n";
//                        comments.setText(commentMsg);

                        gadapter.addItem(droppedItem);
                       // droppedAdapter.notifyDataSetChanged();

                        return true;
                    }else{
                        return false;
                    }


                case DragEvent.ACTION_DRAG_ENDED:
                    if (event.getResult()){
//                        commentMsg += v.getTag() + " : ACTION_DRAG_ENDED - success.\n";
//                        comments.setText(commentMsg);
                    } else {
//                        commentMsg += v.getTag() + " : ACTION_DRAG_ENDED - fail.\n";
//                        comments.setText(commentMsg);
                    };
                    return true;
                default: //unknown case
//                    commentMsg += v.getTag() + " : UNKNOWN !!!\n";
//                    comments.setText(commentMsg);
                    return false;

            }
        }
    }
    private void initPropList(){
        
    }
}

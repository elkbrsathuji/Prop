package il.ac.huji.prop.fragments;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import il.ac.huji.prop.R;
import il.ac.huji.prop.adapters.GroupAdapter;
import il.ac.huji.prop.adapters.ServicesOptionsAdapter;
import il.ac.huji.prop.models.Prop;
import il.ac.huji.prop.models.Service;

/**
 * Created by Android- on 5/10/2015.
 */
public class ServicesManager extends BaseFragment {

    private View mView;
    private ListView serviceOptionsList;
    private ListView propList;
    private Prop list;
    ImageView first,second;
    GroupAdapter gAdapter;
    RelativeLayout target;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.service_manager,null);
        serviceOptionsList = (ListView) mView.findViewById(R.id.social_network_options);
        propList = (ListView) mView.findViewById(R.id.social_network_groups);
        target = (RelativeLayout) mView.findViewById(R.id.droppedTarget);
        target.setOnDragListener(new MyDragEventListener());
        list = new Prop("global",createGlobalList(),0);
        initOptionsList();
        return mView;
    }
//One place to rule them all
    private ArrayList<Service> createGlobalList() {
        ArrayList<Service> networks = new ArrayList<Service>();
        networks.add(new Service("facebook", R.drawable.facebook,false));
        networks.add(new Service("g+", R.drawable.g_plus,false));
        networks.add(new Service("instagram", R.drawable.instagram,false));
        networks.add(new Service("linkedIn", R.drawable.linkedin,false));
        return networks;
    }

    private void initOptionsList(){
        ServicesOptionsAdapter adapter = new ServicesOptionsAdapter(getActivity(),list.getSnList());
        serviceOptionsList.setAdapter(adapter);
        gAdapter = new GroupAdapter(getActivity());
        propList.setAdapter(gAdapter);
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

                        gAdapter.addItem(droppedItem);
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

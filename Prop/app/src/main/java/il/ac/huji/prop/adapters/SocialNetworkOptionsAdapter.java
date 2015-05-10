package il.ac.huji.prop.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import il.ac.huji.prop.R;
import il.ac.huji.prop.fragments.BaseFragment;
import il.ac.huji.prop.models.SocialNetwork;

/**
 * Created by Android- on 5/10/2015.
 */
public class SocialNetworkOptionsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<SocialNetwork> mNetworks;

    public SocialNetworkOptionsAdapter(Context context, ArrayList<SocialNetwork> list){
        mContext=context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mNetworks=list;
    }
    @Override
    public int getCount() {
        return mNetworks==null?0:mNetworks.size();
    }

    @Override
    public Object getItem(int position) {
        return mNetworks==null?null:mNetworks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ImageView iv;

        if (convertView==null){
            convertView=mInflater.inflate(R.layout.row_network_option,null,false);
            iv=(ImageView)convertView.findViewById(R.id.network_icon);
            convertView.setTag(iv);
        }else{
            iv=(ImageView)convertView.getTag();
        }
        iv.setImageResource(mNetworks.get(position).getIcon());
        iv.setOnDragListener(new MyDragListener());
        return convertView;
    }


    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = mContext.getResources().getDrawable(R.drawable.shape_droptarget);
        Drawable normalShape = mContext.getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }
}

package il.ac.huji.prop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import il.ac.huji.prop.R;

/**
* Created by Android- on 5/11/2015.
*/
public class GroupAdapter  extends BaseAdapter{

    private ArrayList<Integer> selected;
    private Context mContext;
    private LayoutInflater mInflater;
    public GroupAdapter(Context context){
        mContext=context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        selected= new ArrayList<>();

    }
    @Override
    public int getCount() {
        return selected==null?0:selected.size();
    }

    @Override
    public Object getItem(int position) {
        return selected==null?null:selected.get(position);
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
        iv.setImageResource(selected.get(position));
        return convertView;
    }

    public void addItem(int i){
        selected.add(i);
        notifyDataSetChanged();
    }
}

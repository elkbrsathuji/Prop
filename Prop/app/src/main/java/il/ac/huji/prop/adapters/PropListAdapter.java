package il.ac.huji.prop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import il.ac.huji.prop.R;
import il.ac.huji.prop.models.ManagerModels.SharedPrefManager;
import il.ac.huji.prop.models.PropList;
import il.ac.huji.prop.models.SocialService;

/**
 * Created by Android- on 8/30/2015.
 */
public class PropListAdapter extends BaseAdapter {
    ArrayList<PropList> mProps;
    Context mContext;
    LayoutInflater mInflater;
    public PropListAdapter(Context context, ArrayList<PropList> props){
        mContext=context;
        mProps =props;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return mProps ==null?0: mProps.size();
    }

    @Override
    public PropList getItem(int position) {
        return mProps ==null?null: mProps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView textView;

        if (convertView==null){
            //holder=new ViewHolder();

            convertView = (View) mInflater.inflate(R.layout.row_prop_service, null, false);

            textView=(TextView)convertView.findViewById(R.id.prop_name);

            convertView.setTag(textView);
        }else{
            textView=(TextView)convertView.getTag();
        }


        textView.setText(mProps.get(position).getName());

        return convertView;
    }

    public void updateList() {
      mProps=  SharedPrefManager.getListOfProps(mContext);
        notifyDataSetChanged();
    }
}

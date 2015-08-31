package il.ac.huji.prop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import il.ac.huji.prop.DataStore;
import il.ac.huji.prop.R;
import il.ac.huji.prop.activities.PropActivity;
import il.ac.huji.prop.models.PropList;
import il.ac.huji.prop.models.SocialService;

/**
 * Created by Android- on 8/30/2015.
 */
public class AllServicesAdapter extends BaseAdapter {

    ArrayList<SocialService> mServices;
    ArrayList<Integer> mPropServices;
    Context mContext;
    LayoutInflater mInflater;
    private PropList prop;

//    public AllServicesAdapter(Context context,int[] propServices){
//
//
//    }

    public AllServicesAdapter(Context context) {
        mContext=context;
        mServices= DataStore.getInstance(mContext).getServicesList();
        mPropServices=new ArrayList<Integer>();

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mServices==null?0:mServices.size();
    }

    @Override
    public Object getItem(int position) {
        return mServices==null?null:mServices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        //User current=mUsers.get(position);
        final SocialService service=mServices.get(position);
        if (convertView==null){
            holder=new ViewHolder();

            convertView = (View) mInflater.inflate(R.layout.row_service, null, false);

            holder.name=(TextView)convertView.findViewById(R.id.service_name);
            holder.icon=(ImageView)convertView.findViewById(R.id.service_icon);
            holder.checkBox=(CheckBox)convertView.findViewById(R.id.service_check_box);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }


        holder.name.setText(service.getName());
holder.icon.setImageResource(service.getIcon());
        if (prop!=null){
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(mPropServices!=null && mPropServices.contains(service.getId()));
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        mPropServices.add(Integer.valueOf(service.getId()));
                    }else{
                        mPropServices.remove(Integer.valueOf(service.getId()));
                    }
                }
            });
        }else{
            holder.checkBox.setVisibility(View.GONE);
        }


        return convertView;
    }

    public int[] getCheckedServices(){
        int[] ret= new int[mPropServices.size()];
        for (int i=0;i<ret.length;i++){
            ret[i]=mPropServices.get(i).intValue();
        }
return ret;

    }

    public void setSpecificProp(PropList prop) {
        this.prop=prop;
      if (prop!=null && prop.getServices()!=null && prop.getServices().length>0) {
          for (int index : prop.getServices()) {
              mPropServices.add(index);
          }
      }else{
          mPropServices= new ArrayList<Integer>();
      }
        notifyDataSetChanged();

    }


    class ViewHolder{

        CheckBox checkBox;
        TextView name;
        ImageView icon;

    }


}

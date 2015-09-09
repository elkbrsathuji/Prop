package il.ac.huji.prop.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import il.ac.huji.prop.DataStore;
import il.ac.huji.prop.R;
import il.ac.huji.prop.models.Post;
import il.ac.huji.prop.models.SocialService;

/**
 * Created by Android- on 9/7/2015.
 */
public class FeedAdapter extends BaseAdapter {

    ArrayList<Post> mPosts;
    Context mContext;
    LayoutInflater mInflater;

    public FeedAdapter(Context context) {
        mContext = context;
        mPosts = DataStore.getInstance(context).getHistory();
        //new ArrayList<Post>();
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mPosts == null ? 0 : mPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return mPosts == null ? null : mPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        Post post = mPosts.get(position);
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = (View) mInflater.inflate(R.layout.row_feed, null, false);

            holder.text = (TextView) convertView.findViewById(R.id.feed_text);
            holder.pic = (ImageView) convertView.findViewById(R.id.feed_pic);
            holder.fbLikes = (TextView) convertView.findViewById(R.id.feed_fb_likes);
            holder.twitterLikes = (TextView) convertView.findViewById(R.id.feed_twitter_likes);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (post.getPhotFile() != null) {
            holder.pic.setImageURI(post.getPhotFile());
        }
        if (!TextUtils.isEmpty(post.getTxt())) {
            holder.text.setText(post.getTxt());
        }
        for (int i = 0; i < post.getmProp().getServices().length; i++) {
            final SocialService so = DataStore.getInstance(mContext).getServicesListAsHash().get(post.getmProp().getServices()[i]);
            so.getLikes(post, post.getmProp().getServices()[i], new SocialService.onFinishGetLikes() {
                @Override
                public void onFinishLikes(int length) {
                    if (so.getName().equalsIgnoreCase("facebook")) {
                        holder.fbLikes.setText(String.valueOf(length));
                        holder.twitterLikes.setText(String.valueOf(0));
                    } else if (so.getName().equalsIgnoreCase("twitter")) {
                        holder.twitterLikes.setText(String.valueOf(length));
                        holder.fbLikes.setText(String.valueOf(0));
                    }else{
                        holder.fbLikes.setText(String.valueOf(0));
                        holder.twitterLikes.setText(String.valueOf(0));
                    }
                }
            });
        }


        return convertView;
    }
//
//    public void addPost(Post post) {
//        mPosts.add( post);
//        notifyDataSetChanged();
//    }


    class ViewHolder {
        ImageView pic;
        TextView text;
        TextView fbLikes;
        TextView twitterLikes;
    }
}

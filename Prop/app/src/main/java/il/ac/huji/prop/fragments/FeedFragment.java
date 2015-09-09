package il.ac.huji.prop.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import il.ac.huji.prop.DataStore;
import il.ac.huji.prop.R;
import il.ac.huji.prop.activities.MainActivity;
import il.ac.huji.prop.adapters.FeedAdapter;
import il.ac.huji.prop.models.Post;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment implements PostFragment.AddPostListener {


    SwipeRefreshLayout mSwipeRefreshLayout;
private ListView mList;
    private FeedAdapter mAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();

        return fragment;
    }

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_feed, container, false);
        mList=(ListView)view.findViewById(R.id.feed_list);
mAdapter=new FeedAdapter(getActivity());
        mList.setAdapter(mAdapter);

        mSwipeRefreshLayout   = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        refreshContent();
    }
});
        return view;
    }

    private void refreshContent() {
       mAdapter.notifyDataSetChanged();
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void addPost(Post post) {
        mAdapter.notifyDataSetChanged();
    }



}

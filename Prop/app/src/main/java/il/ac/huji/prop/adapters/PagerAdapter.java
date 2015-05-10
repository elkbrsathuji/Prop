package il.ac.huji.prop.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.BaseAdapter;

import il.ac.huji.prop.fragments.BaseFragment;
import il.ac.huji.prop.fragments.SocialNetworksManager;

/**
 * Created by Android- on 5/10/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new SocialNetworksManager();
            case 1:
                return new BaseFragment();
            case 2:
                return new BaseFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }

    @Override
    public int getCount() {
        return 3;
    }
}

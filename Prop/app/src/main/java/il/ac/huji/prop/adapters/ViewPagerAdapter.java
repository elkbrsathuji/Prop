package il.ac.huji.prop.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import il.ac.huji.prop.fragments.BaseFragment;
import il.ac.huji.prop.fragments.FeedFragment;
import il.ac.huji.prop.fragments.PostFragment;
import il.ac.huji.prop.fragments.PropFragment;
import il.ac.huji.prop.fragments.ServicesManager;

/**
 * Created by Android- on 5/10/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

      switch (position){
          case 0:


            return PropFragment.newInstance();
          case 1:

          return PostFragment.newInstance();

          case 2:
              return FeedFragment.newInstance();
        }
        return null;

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}

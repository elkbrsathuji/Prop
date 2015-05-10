package il.ac.huji.prop;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import il.ac.huji.prop.adapters.PagerAdapter;
import il.ac.huji.prop.adapters.SocialNetworkOptionsAdapter;
import il.ac.huji.prop.models.SocialNetwork;


public class MainActivity extends FragmentActivity {

    PagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPagerAdapter =
                new PagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
    }



}

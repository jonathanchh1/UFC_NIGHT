package com.example.jonat.services;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    private TabsAdapter mTabsAdapter;
    private CoordinatorLayout mCoordinatorLayout;


    final int[] TabsIcon = new int[]{
            R.drawable.ic_library_books_black_24dp,
            R.drawable.ic_bubble_chart_black_24dp,
            R.drawable.ic_videogame_asset_black_24dp,
            R.drawable.ic_computer_black_24dp
    };


    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.mcoordinator);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.tabspager);
        setupWithViewPager(viewPager);

        //Set Tabs inside Toolbar
        TabLayout mTabs = (TabLayout) findViewById(R.id.tabs);
        mTabs.setupWithViewPager(viewPager);

        //set icon tabs
        if (TabsIcon.length > 0) {
            mTabs.getTabAt(0).setIcon(TabsIcon[0]);
            mTabs.getTabAt(1).setIcon(TabsIcon[1]);
            mTabs.getTabAt(2).setIcon(TabsIcon[2]);
            mTabs.getTabAt(3).setIcon(TabsIcon[3]);
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupWithViewPager(ViewPager viewPager) {
        mTabsAdapter = new TabsAdapter(getSupportFragmentManager());
        mTabsAdapter.addFragment(new EventFragment(), getString(R.string.Event));
        mTabsAdapter.addFragment(new TitleholdersFragment(), getString(R.string.titleholders));
        mTabsAdapter.addFragment(new MediaFragment(), getString(R.string.media));
        mTabsAdapter.addFragment(new FightersFragment(), getString(R.string.fighters));
        mTabsAdapter.addFragment(new NewsFragment(), getString(R.string.news));
        viewPager.setAdapter(mTabsAdapter);
    }
}
   // }
//}

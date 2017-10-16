package com.example.jonat.services;

import android.content.res.Resources;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jonat.services.Adapters.TabsAdapter;
import com.example.jonat.services.Fragments.EventFragment;
import com.example.jonat.services.Fragments.FightersFragment;
import com.example.jonat.services.Fragments.MediasFragment;
import com.example.jonat.services.Fragments.TitleholdersFragment;
import com.github.pedrovgs.DraggablePanel;
import com.github.pedrovgs.DraggableView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();


    private TabsAdapter mTabsAdapter;
    private CoordinatorLayout mCoordinatorLayout;
    private DraggableView draggableView;

    final int[] TabsIcon = new int[]{
            R.drawable.ic_event_seat_black_24dp,
            R.drawable.ic_all_inclusive_black_24dp,
            R.drawable.ic_bubble_chart_black_24dp,
            R.drawable.ic_star_black_24dp,
    };


    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mCoordinatorLayout = findViewById(R.id.mcoordinator);

        draggableView = findViewById(R.id.draggable_view);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.tabspager);
        setupWithViewPager(viewPager);

        //Set Tabs inside Toolbar
        TabLayout mTabs = findViewById(R.id.tabs);
        mTabs.setupWithViewPager(viewPager);

        //set icon tabs
        if (TabsIcon.length > 0) {
            mTabs.getTabAt(0).setIcon(TabsIcon[0]);
            mTabs.getTabAt(1).setIcon(TabsIcon[1]);
            mTabs.getTabAt(2).setIcon(TabsIcon[2]);
            mTabs.getTabAt(3).setIcon(TabsIcon[3]);
        }else{
            Snackbar.make(mCoordinatorLayout, getResources().getString(R.string.tabs), Snackbar.LENGTH_SHORT).show();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.ufc_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initializeDraggablePanel() throws Resources.NotFoundException {
        draggablePanel.setFragmentManager(getSupportFragmentManager());
        draggablePanel.setTopFragment(new EventFragment());
        draggablePanel.initializeView();
    }

    private void setupWithViewPager(ViewPager viewPager) {
        mTabsAdapter = new TabsAdapter(getSupportFragmentManager());
        mTabsAdapter.addFragment(new EventFragment(), getString(R.string.Event));
        mTabsAdapter.addFragment(new TitleholdersFragment(), getString(R.string.titleholders));
        mTabsAdapter.addFragment(new MediasFragment(), getString(R.string.media));
        mTabsAdapter.addFragment(new FightersFragment(), getString(R.string.fighters));
        viewPager.setAdapter(mTabsAdapter);
    }
}
   // }
//}

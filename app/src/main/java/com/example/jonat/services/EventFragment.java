package com.example.jonat.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jonat on 10/11/2017.
 */

public class EventFragment extends Fragment {

    private static final String TAG = EventFragment.class.getSimpleName();
    private UFCAdapter.Callbacks mCallbacks;
    public final static String EVENTS = "events";
    private String mSortBy = EVENTS;
    public UFCContent items;
    private ApiInterface apiService;
    private RecyclerView recyclerView;
    public ProgressBar progressBar;
    public ShareActionProvider mShareActionProvider;

    public EventFragment(){
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.recyclerviewlist, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.mrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.number)));
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mCallback();

        fetchFilms(mSortBy);

        return rootView;

    }

    private void fetchFilms(String mSortBy) {
        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<UFCContent>> call = apiService.getEvents(mSortBy);
        call.enqueue(new Callback<List<UFCContent>>() {
            @Override
            public void onResponse(Call<List<UFCContent>> call, Response<List<UFCContent>> response) {
                int statusCode = response.code();
                List<UFCContent> items = response.body();
                recyclerView.setAdapter(new UFCAdapter(items, R.layout.content_container, getActivity(), mCallbacks));
               progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<List<UFCContent>> call, Throwable t) {
                // Log error here since request failed
                progressBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "connection failed");
            }
        });



    }

    private void updateShareActionProvider(UFCContent items) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, items.getBase_title());
        sharingIntent.putExtra(Intent.EXTRA_TEXT, items.getUrl_name());
        startActivity(Intent.createChooser(sharingIntent, "sharing Option"));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);
        Log.d(TAG, "detail Menu created");

        MenuItem action_share = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(action_share);

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_share:
                //share NEWS
                updateShareActionProvider(items);


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    public void mCallback() {
        mCallbacks = new UFCAdapter.Callbacks() {
            @Override
            public void onItemCompleted(UFCContent items, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.Args, items);
                startActivity(intent);
            }

        };

    }



}

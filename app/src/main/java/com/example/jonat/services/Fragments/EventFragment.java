package com.example.jonat.services.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jonat.services.ApiClient;
import com.example.jonat.services.ApiInterface;
import com.example.jonat.services.DetailActivity;
import com.example.jonat.services.Models.Events;
import com.example.jonat.services.R;
import com.example.jonat.services.Adapters.UFCAdapter;

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
    public Events items;
    private ApiInterface apiService;
    private RecyclerView recyclerView;
    public ProgressBar progressBar;

    public EventFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.recyclerviewlist, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.mrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);


        mCallback();
        fetchFilms(mSortBy);

        return rootView;

    }

    private void fetchFilms(String mSortBy) {
        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Events>> call = apiService.getEvents(mSortBy);
        call.enqueue(new Callback<List<Events>>() {
            @Override
            public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
                int statusCode = response.code();
                List<Events> items = response.body();
                recyclerView.setAdapter(new UFCAdapter(items, R.layout.content_container, getActivity(), mCallbacks));
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<List<Events>> call, Throwable t) {
                // Log error here since request failed
                progressBar.setVisibility(View.VISIBLE);
                Log.d(TAG, getResources().getString(R.string.noconnection));
            }
        });


    }








    public void mCallback() {
        mCallbacks = new UFCAdapter.Callbacks() {

            @Override
            public void onItemCompleted(Events items, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.Args, items);
                startActivity(intent);

            }

        };
    }
}

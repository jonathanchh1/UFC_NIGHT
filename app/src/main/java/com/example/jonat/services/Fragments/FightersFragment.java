package com.example.jonat.services.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jonat.services.Adapters.FightersAdapter;
import com.example.jonat.services.ApiClient;
import com.example.jonat.services.ApiInterface;
import com.example.jonat.services.DetailActivity;
import com.example.jonat.services.Models.Fighters;
import com.example.jonat.services.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jonat on 10/11/2017.
 */

public class FightersFragment extends Fragment {

    private static final String TAG = FightersFragment.class.getSimpleName();
    private FightersAdapter.Callbacks mCallbacks;
    public final static String FIGHTERS = "fighters";
    private String mSortBy = FIGHTERS;
    private ApiInterface apiService;
    private RecyclerView recyclerView;
    public ProgressBar progressBar;

    public FightersFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.recyclerviewlist, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.mrecyclerview);
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

        Call<List<Fighters>> call = apiService.getFighters(mSortBy);
        call.enqueue(new Callback<List<Fighters>>() {
            @Override
            public void onResponse(Call<List<Fighters>> call, Response<List<Fighters>> response) {
                int statusCode = response.code();
                List<Fighters> items = response.body();
                recyclerView.setAdapter(new FightersAdapter(items, R.layout.content_container, getActivity(), mCallbacks));
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<List<Fighters>> call, Throwable t) {
                // Log error here since request failed
                progressBar.setVisibility(View.VISIBLE);
                Log.d(TAG, getResources().getString(R.string.noconnection));
            }
        });


    }








    public void mCallback() {
        mCallbacks = new FightersAdapter.Callbacks() {

            @Override
            public void onItemCompleted(Fighters items, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.Args, items);
                startActivity(intent);

            }

        };
    }
}

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

import com.example.jonat.services.Adapters.MediaAdapter;
import com.example.jonat.services.ApiClient;
import com.example.jonat.services.ApiInterface;
import com.example.jonat.services.DetailActivity;
import com.example.jonat.services.Models.Events;
import com.example.jonat.services.Models.Medias;
import com.example.jonat.services.R;
import com.example.jonat.services.Adapters.UFCAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jonat on 10/11/2017.
 */

public class MediaFragment extends Fragment {
    private static final String TAG = EventFragment.class.getSimpleName();
    private MediaAdapter.Callbacks mCallbacks;
    public final static String MEDIAS = "media";
    private String mSortBy = MEDIAS;
    public Events items;
    private ApiInterface apiService;
    private RecyclerView recyclerView;
    public ProgressBar progressBar;

    public MediaFragment() {
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

        Call<List<Medias>> call = apiService.getMedia(mSortBy);
        call.enqueue(new Callback<List<Medias>>() {
            @Override
            public void onResponse(Call<List<Medias>> call, Response<List<Medias>> response) {
                int statusCode = response.code();
                List<Medias> items = response.body();
                recyclerView.setAdapter(new MediaAdapter(items, R.layout.content_container, getActivity(), mCallbacks));
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<List<Medias>> call, Throwable t) {
                // Log error here since request failed
                progressBar.setVisibility(View.VISIBLE);
                Log.d(TAG, getResources().getString(R.string.noconnection));
            }
        });


    }








    public void mCallback() {
        mCallbacks = new MediaAdapter.Callbacks() {

            @Override
            public void onItemCompleted(Medias items, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.Args, items);
                startActivity(intent);

            }

        };
    }
}

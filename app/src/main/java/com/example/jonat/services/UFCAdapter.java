package com.example.jonat.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jonat on 10/8/2017.
 */

public class UFCAdapter extends RecyclerView.Adapter<UFCAdapter.UFCViewHolder> {

    private List<UFCContent> itemsList;
    private int rowLayout;
    private Context context;
    private final Callbacks mCallbacks;



    public static class UFCViewHolder extends RecyclerView.ViewHolder {
        TextView ufcTitle;
        TextView ufcDescription;
        ImageView thumbnail;
        TextView title_fighters;
        public UFCContent items;
        View mView;


        public UFCViewHolder(View v) {
            super(v);
            mView = v;
            title_fighters = (TextView) v.findViewById(R.id.sub_title);
            ufcTitle = (TextView) v.findViewById(R.id.title);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            ufcDescription = (TextView) v.findViewById(R.id.description);
        }
    }

    public UFCAdapter(List<UFCContent> movies, int rowLayout, Context context, Callbacks mCallbacks) {
        this. itemsList = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mCallbacks = mCallbacks;
    }

    @Override
    public UFCAdapter.UFCViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new UFCViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final UFCViewHolder holder, final int position) {

        final UFCContent mItems =  itemsList.get(position);
        holder.items = mItems;

        String poster = mItems.getFeaturedImage();


        if (!TextUtils.isEmpty(mItems.getFeaturedImage())) {
            Picasso.with(context).load(poster)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.thumbnail,
                            new Callback() {
                                @Override
                                public void onSuccess() {
                                    if (holder.thumbnail != null) {
                                        holder.thumbnail.setVisibility(View.VISIBLE);
                                    } else {
                                        holder.thumbnail.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onError() {
                                    holder.thumbnail.setVisibility(View.VISIBLE);
                                }
                            });


        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallbacks.onItemCompleted(mItems, holder.getAdapterPosition());
            }
        });

        holder.ufcTitle.setText(mItems.getBase_title());
        holder.title_fighters.setText(mItems.getTitle_tag());
        holder.ufcDescription.setText(mItems.getShort_desc());
    }



    @Override
    public int getItemCount() {
        return  itemsList.size();
    }




    public interface Callbacks {
        void onItemCompleted(UFCContent items, int position);


    }
}


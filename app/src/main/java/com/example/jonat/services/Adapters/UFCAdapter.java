package com.example.jonat.services.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonat.services.Models.Events;
import com.example.jonat.services.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jonat on 10/8/2017.
 */

public class UFCAdapter extends RecyclerView.Adapter<UFCAdapter.UFCViewHolder> {

    private List<Events> itemsList;
    private int rowLayout;
    private Context context;
    private final Callbacks mCallbacks;



    public static class UFCViewHolder extends RecyclerView.ViewHolder {
        TextView ufcTitle;
        ImageView thumbnail;
        TextView title_fighters;
        ImageButton shareButton;
        ImageButton FavoriteButton;
        public Events items;
        View mView;


        public UFCViewHolder(View v) {
            super(v);
            mView = v;
            title_fighters = v.findViewById(R.id.sub_title);
            ufcTitle = v.findViewById(R.id.title);
            thumbnail = v.findViewById(R.id.thumbnail);
            shareButton = v.findViewById(R.id.share_button);

        }
    }

    public UFCAdapter(List<Events> events, int rowLayout, Context context, Callbacks mCallbacks) {
        this. itemsList = events;
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

        final Events mItems =  itemsList.get(position);
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
        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, mItems.getBase_title());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, mItems.getUrl_name());
                context.startActivity(Intent.createChooser(sharingIntent, "sharing Option"));

            }
        });
    }


    @Override
    public int getItemCount() {
        return  itemsList.size();
    }




    public interface Callbacks {
        void onItemCompleted(Events items, int position);

    }
}


package com.example.jonat.services.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonat.services.Models.Title;
import com.example.jonat.services.Models.Title;
import com.example.jonat.services.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jonat on 10/11/2017.
 */

public class TitlesAdapter extends RecyclerView.Adapter<TitlesAdapter.TitleViewHolder> {

    private List<Title> itemsList;
    private int rowLayout;
    private Context context;
    private final Callbacks mCallbacks;



    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView ufcTitle;
        ImageView thumbnail;
        TextView description;
        ImageButton shareButton;
        ImageButton FavoriteButton;
        public Title items;
        View mView;


        public TitleViewHolder(View v) {
            super(v);
            mView = v;
            description = (TextView) v.findViewById(R.id.sub_title);
            ufcTitle = (TextView) v.findViewById(R.id.title);
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            shareButton = (ImageButton) v.findViewById(R.id.share_button);

        }
    }

    public TitlesAdapter(List<Title> titles, int rowLayout, Context context, Callbacks mCallbacks) {
        this. itemsList = titles;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mCallbacks = mCallbacks;
    }

    @Override
    public TitlesAdapter.TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TitlesAdapter.TitleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final TitlesAdapter.TitleViewHolder holder, final int position) {

        final Title mItems =  itemsList.get(position);
        holder.items = mItems;

        String poster = mItems.getThumbnail();


        if (!TextUtils.isEmpty(mItems.getThumbnail())) {
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

        holder.ufcTitle.setText(mItems.getLast_name());
        holder.description.setText(mItems.getTitleholders());
        holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, mItems.getTitleholders());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, mItems.getLast_name());
                context.startActivity(Intent.createChooser(sharingIntent, "sharing Option"));

            }
        });
    }


    @Override
    public int getItemCount() {
        return  itemsList.size();
    }




    public interface Callbacks {
        void onItemCompleted(Title items, int position);


    }
}


package com.josmartinez.topmoviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.PosterViewHolder> {

    private final List<Poster> mMovies;
    private final ListItemClickListener mMovieClickListener;

    MovieAdapter(List<Poster> posters, ListItemClickListener listener) {
        this.mMovies = posters;
        this.mMovieClickListener = listener;
    }


    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        FrameLayout inflater = (FrameLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_list_item,viewGroup, false);

        return new PosterViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        Poster poster = mMovies.get(position);
        Picasso.get().load(poster.getPosterPath()).fit().into(holder.listItemPosterView);

    }

    @Override
    public int getItemCount() {
        return  mMovies == null ? 0 : mMovies.size();
    }


    //ViewHolder for MovieAdapter

    class PosterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        ImageView listItemPosterView;

        PosterViewHolder(View itemView) {
            super(itemView);
            listItemPosterView = itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mMovieClickListener.onListItemClick(clickedPosition);
        }

    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


}

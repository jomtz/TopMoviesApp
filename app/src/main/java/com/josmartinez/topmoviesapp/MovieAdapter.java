package com.josmartinez.topmoviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.PosterViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private ListItemClickListener movieClickListener;
    private int numberOfPosters;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }



    public MovieAdapter(int numberOfPosters, ListItemClickListener movieClickListener){
        this.movieClickListener = movieClickListener;
        this.numberOfPosters = numberOfPosters;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return numberOfPosters;
    }


    class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView listItemPosterView;

        PosterViewHolder(View itemView) {
            super(itemView);
            listItemPosterView = itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            movieClickListener.onListItemClick(clickedPosition);
        }
    }



}

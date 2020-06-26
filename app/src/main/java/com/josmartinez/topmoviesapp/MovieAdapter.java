package com.josmartinez.topmoviesapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.PosterViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();

    private int mNumberItems;

    public MovieAdapter(int numberOfItems){
        mNumberItems = numberOfItems;
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
        Log.d(TAG, "#" + position);

    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         private ImageView moviePosterImage;

        public PosterViewHolder (View itemView) {
            super(itemView);
            moviePosterImage = itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }


}

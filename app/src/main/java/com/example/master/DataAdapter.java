package com.example.master;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<MemeInfo> memes;
    private Context context;

    public DataAdapter(Context context, List<MemeInfo> memes) {
        this.context = context;
        this.memes = memes;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.cell_meme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        MemeInfo meme = memes.get(position);
        String mImageAddress = meme.getPhotoUtl();
        Glide
                .with(context)
                .load(mImageAddress)
                .into(holder.imageView);
        holder.nameView.setText(meme.getTitle());
    }

    @Override
    public int getItemCount() {
        return memes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.imageViewMeme);
            nameView = view.findViewById(R.id.textViewTitleMeme);
        }
    }
}

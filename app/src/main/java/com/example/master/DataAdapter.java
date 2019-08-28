package com.example.master;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements View.OnClickListener {

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
        holder.buttonFavorite.setOnClickListener(this);
        holder.layout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return memes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        final ImageButton buttonFavorite;
        final RoundRectCornerConstraintLayout layout;
        ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.imageViewMeme);
            nameView = view.findViewById(R.id.textViewTitleMeme);
            buttonFavorite = view.findViewById(R.id.imageButtonFavorite);
            layout = view.findViewById(R.id.fl_item_container);
        }
    }

    @Override
    public void onClick(View view) {

        boolean pressButton = false;
        switch(view.getId()) {
            case  R.id.imageButtonFavorite:
                ImageButton buttonFavorite = view.findViewById(R.id.imageButtonFavorite);
                Drawable drawable = buttonFavorite.getDrawable();
                if (drawable.getConstantState().equals(view.getResources().getDrawable(R.drawable.baseline_favorite_border_24).getConstantState())) {
                    buttonFavorite.setImageResource(R.drawable.baseline_favorite_24);
                }
                else {
                    buttonFavorite.setImageResource(R.drawable.baseline_favorite_border_24);
                }
                pressButton = true;
            case  R.id.fl_item_container:
                if (!pressButton) {
                View rootView = view.getRootView();
                RecyclerView rc = rootView.findViewById(R.id.my_recycler_view);
                int positionMeme = rc.getChildAdapterPosition(view);

                Intent intent = new Intent(context, DetailedScreenMeme.class);
                intent.putExtra("id", memes.get(positionMeme).getId());
                intent.putExtra("title", memes.get(positionMeme).getTitle());
                intent.putExtra("description", memes.get(positionMeme).getDescription());
                intent.putExtra("isFavorite", memes.get(positionMeme).getIsFavorite());
                intent.putExtra("createdDate", memes.get(positionMeme).getCreatedDate());
                intent.putExtra("photoUtl", memes.get(positionMeme).getPhotoUtl());
                context.startActivity(intent);
                }
        }
    }
}

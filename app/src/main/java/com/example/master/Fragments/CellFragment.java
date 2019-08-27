package com.example.master.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.master.R;

public class CellFragment extends Fragment implements View.OnClickListener {

    public CellFragment() {
    }

    public static CellFragment newInstance() {
        return new CellFragment();
    }

    private boolean isFavorite = false;

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case  R.id.imageButtonFavorite:
                ImageButton buttonFavorite = view.findViewById(R.id.imageButtonFavorite);
                if (!isFavorite) {
                    buttonFavorite.setImageResource(R.drawable.baseline_favorite_24);
                    isFavorite = true;
                }
                else
                {
                    buttonFavorite.setImageResource(R.drawable.baseline_favorite_border_24);
                    isFavorite = false;
                }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.cell_meme, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String mImageAddress = bundle.getString("photoUtl");
            ImageView mImageView = rootView.findViewById(R.id.imageViewMeme);
            Glide
                    .with(this)
                    .load(mImageAddress)
                    .into(mImageView);

            TextView title = rootView.findViewById(R.id.textViewTitleMeme);
            title.setText(bundle.getString("title"));
        }

        ImageButton buttonFavorite = rootView.findViewById(R.id.imageButtonFavorite);
        ImageButton buttonShare = rootView.findViewById(R.id.imageButtonShare);

        buttonFavorite.setOnClickListener(this);
        buttonShare.setOnClickListener(this);

        return rootView;
    }
}

package com.example.master.fragment;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.master.*;
import com.example.master.MainScreenActivity;
import com.example.master.database.AppDatabase;
import com.example.master.database.Meme;
import com.example.master.database.MemeDao;

import java.util.Date;

public class AddMemeFragment extends Fragment {

    private boolean haveImage = false;

    public AddMemeFragment() {
    }

    public static AddMemeFragment newInstance() {
        return new AddMemeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_add_meme, container, false);
        ImageButton buttonClose = view.findViewById(R.id.imageButtonCloseAddMeme);
        final Button buttonCreate = view.findViewById(R.id.buttonCreateMeme);
        ImageButton buttonInputImage = view.findViewById(R.id.imageButtonInputImage);
        final ImageView image = view.findViewById(R.id.imageViewAddMeme);
        final String addressImage = "https://i.ibb.co/G0h8Xnt/2019-08-22-15-11-08.png";
        final TextInputEditText editText = view.findViewById(R.id.textInputEditText);
        final TextInputEditText editTitle = view.findViewById(R.id.textInputEditTitle);

        editText.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                if (!editText.getText().toString().equals("") && !editTitle.getText().toString().equals("") && haveImage)
                    buttonCreate.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        editTitle.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                if (!editText.getText().toString().equals("") && !editTitle.getText().toString().equals("") && haveImage)
                    buttonCreate.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainScreenActivity.class));
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db =  Room.databaseBuilder(view.getContext(),
                        AppDatabase.class, "database")
                        .allowMainThreadQueries()
                        .build();
                MemeDao memeDao = db.memeDao();

                Meme meme = new Meme();
                meme.title = editTitle.getText().toString();
                meme.description = editText.getText().toString();
                meme.photoUtl = addressImage;
                Date date = new Date();
                meme.createdDate = (int)(date.getTime() / 1000) ;
                meme.isFavorite = false;

                memeDao.insert(meme);
            }
        });

        buttonInputImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide
                        .with(view.getContext())
                        .load(addressImage)
                        .into(image);
                haveImage = true;
                if (!editText.getText().toString().equals("") && !editTitle.getText().toString().equals("") && haveImage)
                    buttonCreate.setEnabled(true);
            }
        });

        return view;
    }
}
package com.example.master.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.master.R;

public class AddPostFragment extends Fragment {

    public AddPostFragment() {
    }

    public static AddPostFragment newInstance() {
        return new AddPostFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_post, container, false);
    }
}
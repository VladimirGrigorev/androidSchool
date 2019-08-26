package com.example.master.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.master.R;

public class TapeFragment extends Fragment {

    public TapeFragment() {
    }

    public static TapeFragment newInstance() {
        return new TapeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tape, container, false);
    }
}

package com.example.master.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.master.*;

public class TapeFragment extends Fragment {

    public TapeFragment() {
    }

    public static TapeFragment newInstance() {
        return new TapeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tape, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        DataAdapter adapter = new DataAdapter(view.getContext(), MemesList.listMemes);
        recyclerView.setAdapter(adapter);
        return view;
    }
}

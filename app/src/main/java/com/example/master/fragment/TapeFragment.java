package com.example.master.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.master.*;
import com.example.master.MainScreenActivity;

public class TapeFragment extends Fragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public TapeFragment() {
    }

    public static TapeFragment newInstance()  {
        return new TapeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tape, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        DataAdapter adapter = new DataAdapter(view.getContext(), StaticVariable.listMemes);
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#1EF0D7"));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity(new Intent(getActivity(), MainScreenActivity.class));
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }
}

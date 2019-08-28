package com.example.master.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.master.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class TapeFragment extends Fragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public TapeFragment() {
    }

    public static TapeFragment newInstance() {
        return new TapeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tape, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        DataAdapter adapter = new DataAdapter(view.getContext(), MemesList.listMemes);
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#1EF0D7"));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestMemesForUpdate();
                recyclerView.invalidate();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private void requestMemesForUpdate(){
        NetworkService.getInstance()
                .getJSONApi()
                .getPostDataMemes()
                .enqueue(new Callback<ArrayList<MemeInfo>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<MemeInfo>> call, @NonNull Response<ArrayList<MemeInfo>> response) {
                        if (response.isSuccessful()) {
                            MemesList.listMemes = response.body();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<MemeInfo>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}

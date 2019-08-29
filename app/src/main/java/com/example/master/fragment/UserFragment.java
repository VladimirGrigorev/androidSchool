package com.example.master.fragment;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.master.*;
import com.example.master.database.AppDatabase;
import com.example.master.database.Meme;
import com.example.master.database.MemeDao;
import com.example.master.structure.MemeInfo;
import com.example.master.structure.SharedPreferencesParams;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

    public UserFragment() {
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_user, container, false);
        final CircleImageView image = view.findViewById(R.id.profile_image);
        final String addressImage = "https://i.ibb.co/G0h8Xnt/2019-08-22-15-11-08.png";
        final TextView name = view.findViewById(R.id.textViewProfileName);
        final TextView text = view.findViewById(R.id.textViewProfileText);

        Glide
                .with(view.getContext())
                .load(addressImage)
                .into(image);
        name.setText(StaticVariable.sharedPref.getString(SharedPreferencesParams.username, ""));
        text.setText(StaticVariable.sharedPref.getString(SharedPreferencesParams.userDescription, ""));

        AppDatabase db =  Room.databaseBuilder(view.getContext(),
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        MemeDao memeDao = db.memeDao();

        ProgressBar progressBarProfile = view.findViewById(R.id.progressBarProfile);
        progressBarProfile.setVisibility(ProgressBar.VISIBLE);
        List<Meme> memes = memeDao.getAll();
        ArrayList<MemeInfo> memeInfoArrayList = new ArrayList<>();

        for (int i=0; i<memes.size(); i++) {
            MemeInfo newMeme = new MemeInfo();
            newMeme.setId(String.valueOf(memes.get(i).id));
            newMeme.setCreatedDate(memes.get(i).createdDate);
            newMeme.setDescription(memes.get(i).description);
            newMeme.setIsFavorite(memes.get(i).isFavorite);
            newMeme.setPhotoUtl(memes.get(i).photoUtl);
            newMeme.setTitle(memes.get(i).title);
            memeInfoArrayList.add(newMeme);
        }

        final RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        DataAdapter adapter = new DataAdapter(view.getContext(), memeInfoArrayList);
        recyclerView.setAdapter(adapter);

        progressBarProfile.setVisibility(ProgressBar.INVISIBLE);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.user_screen_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_aboutApp:

                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }
}

package com.example.master.fragment;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.example.master.*;
import com.example.master.database.AppDatabase;
import com.example.master.database.Meme;
import com.example.master.database.MemeDao;
import com.example.master.request.NetworkService;
import com.example.master.structure.MemeInfo;
import com.example.master.structure.SharedPreferencesParams;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Date;
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
        final View view = inflater.inflate(R.layout.fragment_user, container, false);
        final CircleImageView image = view.findViewById(R.id.profile_image);
        final String addressImage = "https://i.ibb.co/G0h8Xnt/2019-08-22-15-11-08.png";
        final TextView name = view.findViewById(R.id.textViewProfileName);
        final TextView text = view.findViewById(R.id.textViewProfileText);
        final ImageView menu = view.findViewById(R.id.imageButtonMenu);
        final Button aboutButton = view.findViewById(R.id.buttonAbout);
        final Button exitButton = view.findViewById(R.id.buttonExit);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutButton.setEnabled(true);
                exitButton.setEnabled(true);
                aboutButton.setVisibility(Button.VISIBLE);
                exitButton.setVisibility(Button.VISIBLE);
            }
        });

        final AlertDialog.Builder ad = new AlertDialog.Builder(view.getContext());
        ad.setMessage("Вы действительно хотите выйти?"); // сообщение
        ad.setPositiveButton("ВЫЙТИ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                requestLogout();
                Intent mStartActivity = new Intent(view.getContext(), SplashScreenActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(view.getContext(), mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) view.getContext().getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            }
        });
        ad.setNegativeButton("ОТМЕНА", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = ad.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.colorToolbar));
            }
        });


        Glide
                .with(view.getContext())
                .load(addressImage)
                .into(image);
        name.setText(StaticVariable.sharedPref.getString(SharedPreferencesParams.username, ""));
        text.setText(StaticVariable.sharedPref.getString(SharedPreferencesParams.userDescription, ""));

        AppDatabase db = Room.databaseBuilder(view.getContext(),
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
        MemeDao memeDao = db.memeDao();

        ProgressBar progressBarProfile = view.findViewById(R.id.progressBarProfile);
        progressBarProfile.setVisibility(ProgressBar.VISIBLE);
        List<Meme> memes = memeDao.getAll();
        ArrayList<MemeInfo> memeInfoArrayList = new ArrayList<>();

        for (int i = 0; i < memes.size(); i++) {
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

    private void requestLogout() {
        NetworkService.getInstance()
                .getJSONApiLogout();
    }
}

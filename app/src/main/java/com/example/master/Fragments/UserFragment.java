package com.example.master.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.TextView;
import android.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.example.master.MainScreenActivity;
import com.example.master.R;
import com.example.master.SharedPreferencesParams;
import com.example.master.StaticVariable;
import de.hdodenhof.circleimageview.CircleImageView;

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

package com.dnoble.software.hearthstonejoust;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Derek on 4/25/16.
 */
public class MainProfileController extends Fragment {

    private JoustProfile currentProfile;
    private JoustMainWindow parentActivity;

    private Button editProfileButton;
    private Button editCollectionButton;
    private Button backButton;

    private TextView profileNameView;
    private ImageView profilePictureView;

    public MainProfileController() {
    }

    public MainProfileController(JoustProfile profile) {
        currentProfile = profile;
    }

    public void showProfile(JoustProfile profile) {
        currentProfile = profile;
        profileNameView.setText(currentProfile.getProfileName());
        profilePictureView.setImageDrawable(currentProfile.getProfilePicture());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parentActivity = (JoustMainWindow) getActivity();

        profileNameView = (TextView) parentActivity.findViewById(R.id.profile_menu_profile_name_view);
        profileNameView.setText(currentProfile.getProfileName());

        profilePictureView = (ImageView) parentActivity.findViewById(R.id.profile_menu_profile_image_view);
        profilePictureView.setImageDrawable(currentProfile.getProfilePicture());

        editProfileButton = (Button) parentActivity.findViewById(R.id.profile_menu_profile_edit_button);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ask the activity to transition to the profile editing screen
                parentActivity.editCurrentProfile();
            }
        });

        editCollectionButton = (Button) parentActivity.findViewById(R.id.profile_menu_collection_edit_button);
        editCollectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ask the activity to transition to the collection editing screen
                parentActivity.editCollection();
            }
        });

        backButton = (Button) parentActivity.findViewById(R.id.profile_menu_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ask the activity to transition back to the profile selection screen
                parentActivity.chooseADifferentProfile(currentProfile);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_menu_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void setProfile(JoustProfile profile) {
        //called by the collection editor and the profile editor when they are finished
        currentProfile = profile;
        //update the fields here?
        profileNameView.setText(currentProfile.getProfileName());
        profilePictureView.setImageDrawable(currentProfile.getProfilePicture());
        //set the new collection
    }

    public JoustProfile getProfile() {
        return currentProfile;
    }
}

package com.dnoble.software.hearthstonejoust;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Derek on 4/18/16.
 */
//main controller for modifying a user profile
public class ProfileEditor extends Fragment {

    private JoustMainWindow parentActivity;
    private MainProfileController profileController;

    private Button doneButton;

    private EditText profileNameView;
    private ImageView profilePictureView;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public ProfileEditor() {
    }

    public ProfileEditor(MainProfileController controller) {
        profileController = controller;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parentActivity = (JoustMainWindow) getActivity();

        profileNameView = (EditText) parentActivity.findViewById(R.id.profile_editor_profile_name);

        profilePictureView = (ImageView) parentActivity.findViewById(R.id.profile_editor_profile_image);
        profilePictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        doneButton = (Button) parentActivity.findViewById(R.id.profile_editor_done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //overwrite the profile with the new changes
                //ask the activity to transition back to the profile menu
                BitmapDrawable image = (BitmapDrawable) profilePictureView.getDrawable();
                JoustProfile newProfile = new JoustProfile(profileNameView.getText().toString(), image, profileController.getProfile().getUserCollection());
                profileController.setProfile(newProfile);
                parentActivity.profileEditorToProfileMenu();
            }
        });

        editProfile(profileController);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_editing_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void editProfile(MainProfileController menu) {
        profileController = menu;
        profileNameView.setText(profileController.getProfile().getProfileName());
        profilePictureView.setImageDrawable(profileController.getProfile().getProfilePicture());

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(parentActivity.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Log.i("SEEKER", "I WAS HERE");
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profilePictureView.setImageBitmap(imageBitmap);
        }
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    parentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}

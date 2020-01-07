package com.dnoble.software.hearthstonejoust;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class JoustMainWindow extends AppCompatActivity {

    private FragmentManager manager;
    private ProfileSelectionController profileSelector;
    private MainProfileController profileMenu;
    private ProfileEditor profileEditor;
    private CollectionEditor collectionEditor;

    private static String PROFILE_NAME_1 = "HS_profile_name_1";
    private static String PROFILE_IMAGE_1 = "HS_profile_image_1";
    private static String PROFILE_COLLECTION_1= "HS_profile_collection_1";
    private static String PROFILE_NAME_2 = "HS_profile_name_2";
    private static String PROFILE_IMAGE_2 = "HS_profile_image_2";
    private static String PROFILE_COLLECTION_2= "HS_profile_collection_2";
    private static String PROFILE_NAME_3 = "HS_profile_name_3";
    private static String PROFILE_IMAGE_3 = "HS_profile_image_3";
    private static String PROFILE_COLLECTION_3= "HS_profile_collection_3";


    //private profiles;
    //private bool firstLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joust_main_window);

        manager = getFragmentManager();
        profileSelector = (ProfileSelectionController) manager.findFragmentById(R.id.joust_window);
        if (profileSelector == null){
            profileSelector = new ProfileSelectionController();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.joust_window, profileSelector);
            transaction.commit();
        }
    }

    public void onProfileSelected(JoustProfile profile) {
        if (profile != null) {
            if (profileMenu == null) {
                profileMenu = new MainProfileController(profile);
                //now we need to hide the listView fragment
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.hide(profileSelector);
                transaction.add(R.id.joust_window, profileMenu);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
            //retrieved the selected profile
            else {
                profileMenu.showProfile(profile);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.hide(profileSelector);
                transaction.show(profileMenu);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    }

    @Override
    protected void onDestroy() {
        //save the files here
        if(profileMenu != null) {
            profileSelector.updateDeselectedProfile(profileMenu.getProfile());
        }

        JoustProfile [] profiles = profileSelector.getProfiles();

        try {
            Log.i("file save", "file saving begins");
            FileOutputStream fostream = openFileOutput(PROFILE_NAME_1, Context.MODE_PRIVATE);
            try {
                fostream.write(profiles[0].getProfileName().getBytes());
                fostream.close();
            }
            catch (IOException f) {
                Log.i("file write error", "write to file 1 name failed");
            }

            fostream = openFileOutput(PROFILE_IMAGE_1, Context.MODE_PRIVATE);
            try {
                profiles[0].getProfilePicture().getBitmap().compress(Bitmap.CompressFormat.PNG, 100, fostream);
                fostream.close();
            }
            catch (IOException f) {
                Log.i("file write error", "write to file 1 image failed");
            }

            fostream = openFileOutput(PROFILE_COLLECTION_1, Context.MODE_PRIVATE);
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(CollectionEditor.getGameCardNumber() * 4);
                IntBuffer intBuffer = byteBuffer.asIntBuffer();
                intBuffer.put(profiles[0].getUserCollection());
                fostream.write(byteBuffer.array());
                fostream.close();
            }
            catch (IOException f) {
                Log.i("file write error", "write to file 1 collection failed");
            }

        }
        catch (FileNotFoundException e) {
            Log.i("File write error", "profile 1 file not found");
        }

        try {
            FileOutputStream fostream = openFileOutput(PROFILE_NAME_2, Context.MODE_PRIVATE);
            try {
                fostream.write(profiles[1].getProfileName().getBytes());
                fostream.close();
            }
            catch (IOException f) {
                Log.i("file write error", "write to file 2 name failed");
            }

            fostream = openFileOutput(PROFILE_IMAGE_2, Context.MODE_PRIVATE);
            try {
                profiles[1].getProfilePicture().getBitmap().compress(Bitmap.CompressFormat.PNG, 100, fostream);
                fostream.close();
            }
            catch (IOException f) {
                Log.i("file write error", "write to file 2 image failed");
            }

            fostream = openFileOutput(PROFILE_COLLECTION_2, Context.MODE_PRIVATE);
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(CollectionEditor.getGameCardNumber() * 4);
                IntBuffer intBuffer = byteBuffer.asIntBuffer();
                intBuffer.put(profiles[1].getUserCollection());
                fostream.write(byteBuffer.array());
                fostream.close();
            }
            catch (IOException f) {
                Log.i("file write error", "write to file 2 collection failed");
            }
        }
        catch (FileNotFoundException e) {
            Log.i("File write error", "profile 2 file not found");
        }

        try {
            FileOutputStream fostream = openFileOutput(PROFILE_NAME_3, Context.MODE_PRIVATE);
            try {
                fostream.write(profiles[2].getProfileName().getBytes());
                fostream.close();
            }
            catch (IOException f) {
                Log.i("file write error", "write to file 3 name failed");
            }

            fostream = openFileOutput(PROFILE_IMAGE_3, Context.MODE_PRIVATE);
            try {
                profiles[2].getProfilePicture().getBitmap().compress(Bitmap.CompressFormat.PNG, 100, fostream);
                fostream.close();
            }
            catch (IOException f) {
                Log.i("file write error", "write to file 3 image failed");
            }

            fostream = openFileOutput(PROFILE_COLLECTION_3, Context.MODE_PRIVATE);
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(CollectionEditor.getGameCardNumber() * 4);
                IntBuffer intBuffer = byteBuffer.asIntBuffer();
                intBuffer.put(profiles[2].getUserCollection());
                fostream.write(byteBuffer.array());
                fostream.close();
            }
            catch (IOException f) {
                Log.i("file write error", "write to file 3 collection failed");
            }
        }
        catch (FileNotFoundException e) {
            Log.i("File write error", "profile 3 file not found");
        }

        super.onDestroy();
    }

    public void chooseADifferentProfile(JoustProfile profile) {
        profileSelector.updateDeselectedProfile(profile);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(profileMenu);
        transaction.show(profileSelector);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    public void editCurrentProfile() {
        if (profileEditor == null) {
            profileEditor = new ProfileEditor(profileMenu);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(profileMenu);
            transaction.add(R.id.joust_window, profileEditor);
            //transaction.addToBackStack(null);
            transaction.commit();
        }
        //retrieved the selected profile
        else {
            profileEditor.editProfile(profileMenu);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(profileMenu);
            transaction.show(profileEditor);
            //transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void profileEditorToProfileMenu() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(profileEditor);
        transaction.show(profileMenu);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    public void collectionEditorToProfileMenu() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(collectionEditor);
        transaction.show(profileMenu);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    public void editCollection() {
        if (collectionEditor == null) {
            collectionEditor = new CollectionEditor(profileMenu);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(profileMenu);
            transaction.add(R.id.joust_window, collectionEditor);
            //transaction.addToBackStack(null);
            transaction.commit();
        }
        //retrieved the selected profile
        else {
            collectionEditor.editCollection(profileMenu);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(profileMenu);
            transaction.show(collectionEditor);
            //transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public ArrayList<JoustProfile> loadProfiles() {


        ArrayList<JoustProfile> profiles = new ArrayList<JoustProfile>();

        try {
            FileInputStream fistream = openFileInput(PROFILE_NAME_1);
            try {
                fistream.close();
            }
            catch (IOException e) {
            }
        }
        catch (FileNotFoundException e) {
            int cardNumber = CollectionEditor.getGameCardNumber();
            Resources res = getResources();
            BitmapDrawable defaultImage = (BitmapDrawable) res.getDrawable(R.drawable.default_picture);
            int[] emptyCollection = new int[cardNumber];
            Arrays.fill(emptyCollection, 0);
            Log.i("local memory", "no files present");
            profiles.add(new JoustProfile("Profile 1", defaultImage, emptyCollection));
            profiles.add(new JoustProfile("Profile 2", defaultImage, emptyCollection));
            profiles.add(new JoustProfile("Profile 3", defaultImage, emptyCollection));
            return profiles;
        }
        String [] names = loadNames();
        BitmapDrawable[] images = loadImages();
        int [] [] collections = loadCollections();

        for(int i = 0; i < names.length; i++) {
            profiles.add(new JoustProfile(names[i], images[i], collections[i]));
        }
        Log.i("local memory","files loaded");
        return profiles;

    }

    private String [] loadNames() {
        String [] names = new String[3];
        try {
            byte [] buffer = new byte[1000];
            FileInputStream fistream = openFileInput(PROFILE_NAME_1);
            try {
                fistream.read(buffer);
                names[0] = new String(buffer);
            }
            catch(IOException e) {

            }
        }
        catch(FileNotFoundException e) {

        }
        try {
            byte [] buffer = new byte[1000];
            FileInputStream fistream = openFileInput(PROFILE_NAME_2);
            try {
                fistream.read(buffer);
                names[1] = new String(buffer);
            }
            catch(IOException e) {

            }
        }
        catch(FileNotFoundException e) {

        }

        try {
            byte [] buffer = new byte[100];
            FileInputStream fistream = openFileInput(PROFILE_NAME_3);
            try {
                fistream.read(buffer);
                names[2] = new String(buffer);
            }
            catch(IOException e) {

            }
        }
        catch(FileNotFoundException e) {

        }
        return names;
    }

    private BitmapDrawable [] loadImages() {
        BitmapDrawable [] images = new BitmapDrawable[3];
        try {
            FileInputStream fistream = openFileInput(PROFILE_IMAGE_1);
            images[0] = new BitmapDrawable(getResources(), fistream);
        }
        catch(FileNotFoundException e) {

        }
        try {
            FileInputStream fistream = openFileInput(PROFILE_IMAGE_2);
            images[1] = new BitmapDrawable(getResources(), fistream);
        }
        catch(FileNotFoundException e) {

        }
        try {
            FileInputStream fistream = openFileInput(PROFILE_IMAGE_3);
            images[2] = new BitmapDrawable(getResources(), fistream);
        }
        catch(FileNotFoundException e) {

        }
        return images;
    }

    private int [] [] loadCollections() {
        int[][] collections = new int[3][];

        try {
            byte[] buffer = new byte[4 * CollectionEditor.getGameCardNumber()];
            FileInputStream fistream = openFileInput(PROFILE_COLLECTION_1);
            try {
                fistream.read(buffer, 0, 4 * CollectionEditor.getGameCardNumber());
                IntBuffer intBuf =
                        ByteBuffer.wrap(buffer)
                                .order(ByteOrder.BIG_ENDIAN)
                                .asIntBuffer();
                collections[0] = new int[CollectionEditor.getGameCardNumber()];
                intBuf.get(collections[0]);
            }
            catch (IOException e) {

            }
        }
        catch(FileNotFoundException e) {

        }

        try {
            byte[] buffer = new byte[4 * CollectionEditor.getGameCardNumber()];
            FileInputStream fistream = openFileInput(PROFILE_COLLECTION_2);
            try {
                fistream.read(buffer, 0, 4 * CollectionEditor.getGameCardNumber());
                IntBuffer intBuf =
                        ByteBuffer.wrap(buffer)
                                .order(ByteOrder.BIG_ENDIAN)
                                .asIntBuffer();
                collections[1] = new int[CollectionEditor.getGameCardNumber()];
                intBuf.get(collections[1]);
            }
            catch (IOException e) {

            }
        }
        catch(FileNotFoundException e) {

        }

        try {
            byte[] buffer = new byte[4 * CollectionEditor.getGameCardNumber()];
            FileInputStream fistream = openFileInput(PROFILE_COLLECTION_3);
            try {
                fistream.read(buffer, 0, 4 * CollectionEditor.getGameCardNumber());
                IntBuffer intBuf =
                        ByteBuffer.wrap(buffer)
                                .order(ByteOrder.BIG_ENDIAN)
                                .asIntBuffer();
                collections[2] = new int[CollectionEditor.getGameCardNumber()];
                intBuf.get(collections[2]);
            }
            catch (IOException e) {

            }
        }
        catch(FileNotFoundException e) {

        }

        return collections;
    }

}

package com.dnoble.software.hearthstonejoust;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by Derek on 4/25/16.
 */
public class JoustProfile extends Object {
    private String profileName;
    private BitmapDrawable profilePicture;
    private int[] userCollection;


    public JoustProfile() {
        profileName = null;
        profilePicture = null;
        userCollection = null;
    }
    public JoustProfile(String name, BitmapDrawable picture, int[] collection) {
        profileName = name;
        profilePicture = picture;
        userCollection = collection;
    }

    public String toString() {
        return profileName;
    }

    public String getProfileName() {
        return profileName;
    }

    public BitmapDrawable getProfilePicture(){
        return profilePicture;
    }

    public int[] getUserCollection() {
        return userCollection;
    }

    public void setProfileName(String name) {
        profileName = name;
    }

    public void setProfilePicture(BitmapDrawable picture) {
        profilePicture = picture;
    }

    public void setUserCollection(int[] collection) {
        userCollection = collection;
    }

}

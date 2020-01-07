package com.dnoble.software.hearthstonejoust;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Derek on 4/18/16.
 */
public class ProfileSelectionController extends Fragment {
    private ListView profileList;
    private JoustMainWindow parentActivity;

    private ArrayAdapter<JoustProfile> profileListAdapter;

    private ArrayList<JoustProfile> profiles;
    private int currentProfileIndex;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_selection_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parentActivity = (JoustMainWindow) getActivity();

        profileList = (ListView) parentActivity.findViewById(R.id.profile_selection_profile_list_view);
        //we would load the profile list here or retrieve it from the activity
        profiles = parentActivity.loadProfiles();
        profileListAdapter = new ArrayAdapter<JoustProfile>(parentActivity, android.R.layout.simple_list_item_1, profiles);
        profileList.setAdapter(profileListAdapter);
        profileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position is the array index of the selected item
                currentProfileIndex = position;
                Adapter array = (Adapter) profileList.getAdapter();
                JoustProfile profile = (JoustProfile) array.getItem(position);
                parentActivity.onProfileSelected(profile);

                //retrieve the power object at the position
                //notify the activity that it is time to transition to the next fragment and tell it what object was selected
            }
        });

    }

    public void updateDeselectedProfile(JoustProfile profile) {
        profileListAdapter.remove(profileListAdapter.getItem(currentProfileIndex));
        profileListAdapter.insert(profile, currentProfileIndex);
    }

    public JoustProfile[] getProfiles() {
        JoustProfile[] array = new JoustProfile[0];
        return profiles.toArray(array);
    }

}

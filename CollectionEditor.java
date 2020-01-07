/**
 * Created by Derek on 4/18/16.
 */

//this will be the the most complex feature of the app, and the one part transplanted from the mac version
    //implements swipe gestures & stuff
package com.dnoble.software.hearthstonejoust;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CollectionEditor extends Fragment {

    private MainProfileController profileController;
    private JoustMainWindow parentActivity;

    private Button addButton;
    private Button removeButton;
    private Button nextButton;
    private Button previousButton;
    private Button doneButton;
    private ImageView cardDisplay;
    private TextView cardCountDisplay;

    final static private HSCard[] gameCards = new HSCard[5]; //stores all of the current hearthstone cards
    private int[] newCollection;

    private int currentCardIndex;

    public CollectionEditor() {

    }

    public CollectionEditor(MainProfileController controller) {
        profileController = controller;
        newCollection = profileController.getProfile().getUserCollection();
        currentCardIndex = 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parentActivity = (JoustMainWindow) getActivity();

        cardCountDisplay = (TextView) parentActivity.findViewById(R.id.collection_editor_card_count_display);

        addButton = (Button) parentActivity.findViewById(R.id.collection_editor_add_card_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newCollection[currentCardIndex] == 1 && gameCards[currentCardIndex].isLegendary()) {

                }
                else if(newCollection[currentCardIndex] == 2) {

                }
                else {
                    newCollection[currentCardIndex]++;
                }
                cardCountDisplay.setText("" + newCollection[currentCardIndex]);
            }
        });

        removeButton = (Button) parentActivity.findViewById(R.id.collection_editor_remove_card_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newCollection[currentCardIndex] != 0) {
                    newCollection[currentCardIndex]--;
                }
                cardCountDisplay.setText("" + newCollection[currentCardIndex]);
            }
        });

        nextButton = (Button) parentActivity.findViewById(R.id.collection_editor_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCardIndex < gameCards.length) {
                    currentCardIndex++;
                    cardCountDisplay.setText("" + newCollection[currentCardIndex]);
                    cardDisplay.setImageDrawable(gameCards[currentCardIndex].getCardArt());
                }
            }
        });

        previousButton = (Button) parentActivity.findViewById(R.id.collection_editor_previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCardIndex > 0) {
                    currentCardIndex--;
                    cardCountDisplay.setText("" + newCollection[currentCardIndex]);
                    cardDisplay.setImageDrawable(gameCards[currentCardIndex].getCardArt());
                }

            }
        });

        doneButton = (Button) parentActivity.findViewById(R.id.collection_editor_done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoustProfile initialProfile = profileController.getProfile();
                JoustProfile updatedProfile = new JoustProfile(initialProfile.getProfileName(), initialProfile.getProfilePicture(), newCollection);
                profileController.setProfile(updatedProfile);
                 parentActivity.collectionEditorToProfileMenu();
            }
        });

        cardDisplay = (ImageView) parentActivity.findViewById(R.id.collection_editor_card_view);
        setGameCards();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.collection_editing_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        editCollection(profileController);
    }

    public static int getGameCardNumber() {
        return gameCards.length;
    }

    public void editCollection(MainProfileController menu) {
        profileController = menu;
        newCollection = profileController.getProfile().getUserCollection();
        JoustProfile profile = profileController.getProfile();

        cardCountDisplay.setText("" + newCollection[currentCardIndex]);

        cardDisplay.setImageDrawable(gameCards[currentCardIndex].getCardArt());
    }

    private void setGameCards() {
        //instantiate the game cards. This will be a huge method eventually (at least 303 lines)
        Resources res = parentActivity.getResources();

        gameCards[0] = new HSCard(res.getDrawable(R.drawable.acolyte_of_pain), false, false);
        gameCards[1] = new HSCard(res.getDrawable(R.drawable.alakir_the_windlord), true, false);
        gameCards[2] = new HSCard(res.getDrawable(R.drawable.alarm_o_bot), false, false);
        gameCards[3] = new HSCard(res.getDrawable(R.drawable.aldor_peacekeeper), false, false);
        gameCards[4] = new HSCard(res.getDrawable(R.drawable.cruel_taskmaster), false, false);

    }

}

package com.dnoble.software.hearthstonejoust;

import android.graphics.drawable.Drawable;

/**
 * Created by Derek on 4/25/16.
 */
public class HSCard {
    private Drawable cardArt;
    private boolean isLegendary;
    private boolean isBasic;

    public HSCard() {
        cardArt = null;
        isLegendary = false;
        isBasic = false;
    }

    public HSCard(Drawable art, boolean legendary, boolean basic) {
        cardArt = art;
        isLegendary = legendary;
        isBasic = basic;
    }

    public boolean isBasic() {
        return isBasic;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public Drawable getCardArt() {
        return cardArt;
    }

}
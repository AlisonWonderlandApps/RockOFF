package com.example.csit321sl01a.cardtaskr;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * INIT ON LOAD
 */
public class CardImageList
{
    ArrayList<Integer> cardImageList;


    public CardImageList(Context context)
    {
        //make List
        cardImageList = new ArrayList<>();
        TypedArray images = context.getResources().obtainTypedArray(R.array.cardImages);
        for(int i=0; i<images.length(); i++)
        {
            cardImageList.add(images.getResourceId(i, 0));
        }
    }

    public int getCardImage(int index)
    {
        return cardImageList.get(index);
    }


}

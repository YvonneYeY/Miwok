package com.a2017.yvonne.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yvonne on 2017/2/15.
 */

public class WordAdapter extends ArrayAdapter<Word>{
    private int colorId;
    public WordAdapter(Activity context, ArrayList<Word> word,int mcolorId){
        super(context, 0, word);
        colorId=mcolorId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            final Word currentword = getItem(position);

                // Find the TextView in the list_item.xml layout with the ID version_name
                TextView nameTextView = (TextView) listItemView.findViewById(R.id.miwok_textView1);
                // Get the version name from the current AndroidFlavor object and
                // set this text on the name TextView
                nameTextView.setText(currentword.getmMiwokTranslation());

                // Find the TextView in the list_item.xml layout with the ID version_number
                TextView numberTextView = (TextView) listItemView.findViewById(R.id.default_textView2);
                // Get the version number from the current AndroidFlavor object and
                // set this text on the number TextView
                numberTextView.setText(currentword.getmDefaultTransLation());
                ImageView imageView=(ImageView)listItemView.findViewById(R.id.image_view);
            if(currentword.hasImage()){
                imageView.setImageResource(currentword.getImageSourceId());
                imageView.setVisibility(View.VISIBLE);

            }else if(currentword.hasImage()==false){
                imageView.setVisibility(View.GONE);
            }
            View textcontationer =(LinearLayout)listItemView.findViewById(R.id.textcontainer);
            int color= ContextCompat.getColor(getContext(),colorId);
            textcontationer.setBackgroundColor(color);

            }



        return listItemView;
    }
}

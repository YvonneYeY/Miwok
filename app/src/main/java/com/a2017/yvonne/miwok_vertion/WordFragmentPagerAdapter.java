package com.a2017.yvonne.miwok_vertion;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Yvonne on 2017/2/17.
 */

public class WordFragmentPagerAdapter extends FragmentPagerAdapter{
    private Context mContext;
    public WordFragmentPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new NumberFragment();
        }else if(position==1){
            return new FamilyFragment();

        }else if(position==2){
            return new ColorFragment();
        }else {
            return new PhraeseFragment();
        }
    }
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if (position == 2) {
            return mContext.getString(R.string.category_colors);
        } else {
            return mContext.getString(R.string.category_phrases);
        }
    }
}

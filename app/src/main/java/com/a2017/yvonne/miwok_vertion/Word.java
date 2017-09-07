package com.a2017.yvonne.miwok_vertion;

/**
 * Created by Yvonne on 2017/2/14.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private final int NOIMAGEID = -1;
    private int imageSourceId = NOIMAGEID;
    private int soundSourceId;

    public Word(String mDefaultTranslation, String mMiwokTranslation, int imageSourceId, int soundSourceId) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
        this.imageSourceId = imageSourceId;
        this.soundSourceId = soundSourceId;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int soundSourceId) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
        this.soundSourceId = soundSourceId;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getmDefaultTransLation() {
        return mDefaultTranslation;
    }

    public int getImageSourceId() {
        return imageSourceId;
    }

    public int getSoundSourceId() {
        return soundSourceId;
    }

    public boolean hasImage() {
        return imageSourceId != NOIMAGEID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", NOIMAGEID=" + NOIMAGEID +
                ", imageSourceId=" + imageSourceId +
                ", soundSourceId=" + soundSourceId +
                '}';
    }
}




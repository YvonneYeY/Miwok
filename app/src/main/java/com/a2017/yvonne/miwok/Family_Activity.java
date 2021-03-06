package com.a2017.yvonne.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class Family_Activity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mAudioChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    else if (focusChange == AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }  else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    }
                }};

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioChangeListener);

        }
    }
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    MediaPlayer.OnCompletionListener completionListener=new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        setContentView(R.layout.word_list);


        final ArrayList<Word> fammily = new ArrayList<Word>();
        fammily.add(new Word("father","əpə",R.drawable.family_father,R.raw.family_father));
        fammily.add(new Word("mother","ata",R.drawable.family_mother,R.raw.family_mother));
        fammily.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        fammily.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        fammily.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        fammily.add(new Word("younger broder","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        fammily.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        fammily.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        fammily.add(new Word("grandfather","ama",R.drawable.family_grandfather,R.raw.family_grandfather));
        fammily.add(new Word("grandmother","paapa",R.drawable.family_grandmother,R.raw.family_grandmother));

        WordAdapter familyAdapter=new WordAdapter(this,fammily,R.color.category_family);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(familyAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word =fammily.get(position);
                releaseMediaPlayer();
                mMediaPlayer= MediaPlayer.create(Family_Activity.this,word.getSoundSourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(completionListener);
            }
        });


    }
}

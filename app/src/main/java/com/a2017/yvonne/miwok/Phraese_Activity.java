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

public class Phraese_Activity extends AppCompatActivity {
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

        final ArrayList<Word> phraese = new ArrayList<Word>();
        phraese.add(new Word("Where are you" +
                "going?","minto wuksus",R.raw.phrase_where_are_you_going));
        phraese.add(new Word("What is your name?","tinnə oyaase'nə",R.raw.phrase_what_is_your_name));
        phraese.add(new Word("My name is...","oyaaset..",R.raw.phrase_my_name_is));
        phraese.add(new Word("How are you feeling?","michəksəs?",R.raw.phrase_how_are_you_feeling));
        phraese.add(new Word("I’m feeling good","kuchi achit",R.raw.phrase_im_feeling_good));
        phraese.add(new Word("Are you coming?","əənəs'aa?",R.raw.phrase_are_you_coming));
        phraese.add(new Word("Yes, I’m coming","həə’ əənəm",R.raw.phrase_yes_im_coming));
        phraese.add(new Word("I’m coming","əənəm",R.raw.phrase_im_coming));
        phraese.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        phraese.add(new Word("Come here","ənni'nem",R.raw.phrase_come_here));

        WordAdapter phraesetAdapter=new WordAdapter(this,phraese,R.color.category_phrases);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(phraesetAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=phraese.get(position);
                releaseMediaPlayer();
                mMediaPlayer=MediaPlayer.create(Phraese_Activity.this,word.getSoundSourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(completionListener);
            }
        });
    }
}

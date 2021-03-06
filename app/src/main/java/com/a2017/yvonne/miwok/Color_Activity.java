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

public class Color_Activity extends AppCompatActivity {
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

        final ArrayList<Word> color= new ArrayList<Word>();
        color.add(new Word("red","weleth",R.drawable.color_red,R.raw.color_red));
        color.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        color.add(new Word("brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        color.add(new Word("gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        color.add(new Word("black","kalulli",R.drawable.color_black,R.raw.color_black));
        color.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        color.add(new Word("dusty yellow","topiisa",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        color.add(new Word("mustard yellow","chiwiita",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordAdapter colorAdaper=new WordAdapter(this,color,R.color.category_colors);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(colorAdaper);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word =color.get(position);
                releaseMediaPlayer();
                mMediaPlayer=MediaPlayer.create(Color_Activity.this,word.getSoundSourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(completionListener);
            }
        });


    }
}

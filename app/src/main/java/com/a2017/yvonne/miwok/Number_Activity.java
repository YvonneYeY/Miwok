package com.a2017.yvonne.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class Number_Activity extends AppCompatActivity {
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

    @Override
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


//        初始化mAudioManager
        mAudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        setContentView(R.layout.word_list);
//        String[] word = new String[10];
//        word[0] = "one";
//        word[1] = "two";
//        word[2] = "three";
//        word[3] = "four";
//        word[4] = "five";
//        word[5] = "six";
//        word[6] = "seven";
//        word[7] = "eight";
//        word[8] = "nine";
//        word[9] = "ten";
//
//        List<String> words= Arrays.asList(word);
//        ArrayList<String> words = new ArrayList<>();
//        words.add("one");
//        words.add("two");
//        words.add("three");
//        words.add("four");
//        words.add("five");
//        words.add("six");
//        words.add("seven");
//        words.add("eight");
//        words.add("nine");
//        words.add("ten");
//        ArrayList<String> wordswimok = new ArrayList<>();
//        words.add("lutti");
//        words.add("two");
//        words.add("three");
//        words.add("four");
//        words.add("five");
//        words.add("six");
//        words.add("seven");
//        words.add("eight");
//        words.add("nine");
//        words.add("ten");
       final ArrayList<Word> words =new ArrayList<Word>();
        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","ottiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));



//        LinearLayout rootView = (LinearLayout) findViewById(rootView);
//        int i=0;
//        int size=words.size();
//
//        while(i<size){
//            TextView windowView=new TextView(this);
//            windowView.setText(words.get(i));
//            rootView.addView(windowView);
//            i++;
//
//        }
//        for(int i=0;i<words.size();i++){
//            TextView windowView=new TextView(this);
//            windowView.setText(words.get(i));
//            rootView.addView(windowView);
//        }
//        ArrayAdapter itemAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, words);
//        ListView listView = (ListView) findViewById(R.id.list);
////        listView.setAdapter(itemAdapter);
//        GridView gridview =(GridView)findViewById(R.id.gridview);
////        Log.v("Number_Activity", "word at index 3 is: " + words.get(3));
//        gridview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,words));
        WordAdapter itemAdapter=new WordAdapter(this,words,R.color.category_numbers);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(itemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mAudioChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback

                    Log.v("NumbersActivity", "Current word: " + word);
                    mMediaPlayer= MediaPlayer.create(Number_Activity.this,word.getSoundSourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });
    }
    }

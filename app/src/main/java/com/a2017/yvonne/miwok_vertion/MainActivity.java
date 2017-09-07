package com.a2017.yvonne.miwok_vertion;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        WordFragmentPagerAdapter adapter = new WordFragmentPagerAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tab=(TabLayout)findViewById(R.id.tabs);
        tab.setupWithViewPager(viewPager);


//        TextView numbers=(TextView)findViewById(R.id.numbers);
//        numbers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent numbersIntent=new Intent(MainActivity.this,Number_Activity.class);
//                startActivity(numbersIntent);
//            }
//        });
//    }
        //    public void openNumberActivity(View view){
//        Intent intent=new Intent(this,Number_Activity.class);
//        startActivity(intent);
//    }
//    public void openFamliyActivity(View view){
//        Intent intent=new Intent(this,Family_Activity.class);
//        startActivity(intent);
//    }
//    public void openColorActivity(View view){
//        Intent intent=new Intent(this,Color_Activity.class);
//        startActivity(intent);
//    }
//    public void openPharasesActivity(View view){
//        Intent intent=new Intent(this,Phraese_Activity.class);
//        startActivity(intent);
//    }

    }
}

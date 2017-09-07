package com.a2017.yvonne.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView numbers=(TextView)findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent=new Intent(MainActivity.this,Number_Activity.class);
                startActivity(numbersIntent);
            }
        });
    }
//    public void openNumberActivity(View view){
//        Intent intent=new Intent(this,Number_Activity.class);
//        startActivity(intent);
//    }
    public void openFamliyActivity(View view){
        Intent intent=new Intent(this,Family_Activity.class);
        startActivity(intent);
    }
    public void openColorActivity(View view){
        Intent intent=new Intent(this,Color_Activity.class);
        startActivity(intent);
    }
    public void openPharasesActivity(View view){
        Intent intent=new Intent(this,Phraese_Activity.class);
        startActivity(intent);
    }

}

package com.makerlabperu.hangdroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FONT CHANGER
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/cosmic.ttf");
        TextView myTextView = (TextView)findViewById(R.id.textView);
        myTextView.setTypeface(myTypeface);
    }

    public void startSinglePlayerGame(View v) {
        Intent myIntent = new Intent(this, GameActivity.class);
        startActivity(myIntent);
    }

    public void startMultiplayerGame(View v){
        Intent myIntent = new Intent(this, MultiplayerActivity.class);
        startActivity(myIntent);
    }

    public void startScoreScreen(View v){
        Intent myIntent = new Intent(this, ScoresActivity.class);
        startActivity(myIntent);
    }
}

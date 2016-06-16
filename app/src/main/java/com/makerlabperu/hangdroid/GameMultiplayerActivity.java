package com.makerlabperu.hangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GameMultiplayerActivity extends AppCompatActivity {

    String mWord;
    int mFailCounter = 0;
    int mGuessedLetter = 0;
    int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_game);

        String wordSent = getIntent().getStringExtra("WORD_IDENTIFIER");

        Log.d("MYLOG", wordSent);

        mWord = wordSent;

        createTextViews(wordSent);
    }

    /**
     * Retrieve the letter inserted and the editText
     * @param v (Button Click)
     */
    public void introduceLetter(View v){
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);
        String letter = myEditText.getText().toString();

        myEditText.setText("");

        Log.d("MYLOG", "The letter is "+ letter);

        if (letter.length() == 1) {
            checkLetter(letter.toUpperCase());
        } else {
                Toast.makeText(this, "Please Introduce Letter", Toast.LENGTH_SHORT).show();
            }
    }

    /**
     *  Checking if the letter introduced matches any letter in the word to guess
     * @param introduceLetter, letter introduced by user
     */

    public void checkLetter(String introduceLetter){

        boolean letterGuessed = false;
        char charIntroduced = introduceLetter.charAt(0);

        for (int i=0; i < mWord.length() ; i++){
            char charFromTheWord = mWord.charAt(i);

            if (charFromTheWord == introduceLetter.charAt(0)){

                Log.d ("MyLog", "There was one match");

                letterGuessed = true;
                showLettersAtIndex(i, charIntroduced);

                mGuessedLetter++;
            }
        }

        if (!letterGuessed){
            letterFailed(Character.toString(charIntroduced));
        }

        if(mGuessedLetter == mWord.length()){
            finish();
        }
    }

    public void createTextViews(String word){
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        for (int i = 0; i < word.length(); i++){
            TextView  newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview, null);

            layoutLetters.addView(newTextView);
        }

    }

    public void clearScreen(){
        TextView textViewFailed = (TextView) findViewById(R.id.textView6);
        textViewFailed.setText("");

        mGuessedLetter = 0;
        mFailCounter = 0;


        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        for (int i = 0; i < layoutLetters.getChildCount(); i++){
            TextView currentTextView = (TextView) layoutLetters.getChildAt(i);
            currentTextView.setText("_");
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
    }


    public void letterFailed(String letterFailed){

        TextView textViewFailed = (TextView) findViewById(R.id.textView6);

        String previousFail = textViewFailed.getText().toString();
        textViewFailed.setText(previousFail + letterFailed);

        mFailCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (mFailCounter == 1) {
            imageView.setImageResource(R.drawable.hangdroid_1);
        }  else if (mFailCounter == 2){
            imageView.setImageResource(R.drawable.hangdroid_2);
        }  else if (mFailCounter == 3){
            imageView.setImageResource(R.drawable.hangdroid_3);
        }  else if (mFailCounter == 4){
            imageView.setImageResource(R.drawable.hangdroid_4);
        }  else if (mFailCounter == 5){
            imageView.setImageResource(R.drawable.hangdroid_5);
        } else if (mFailCounter == 6){
            Intent gameOverIntent = new Intent(this, GameOverActivity.class);

            gameOverIntent.putExtra("POINTS_IDENTIFIER", mPoints);

            startActivity(gameOverIntent);
        }
    }

    /**
     *  Displaying a letter guessed by the user
     * @param position of the letter
     * @param letterGuessed
     */
    public void showLettersAtIndex(int position, char letterGuessed){

        LinearLayout layoutletter = (LinearLayout) findViewById(R.id.layoutLetters);

        TextView textView = (TextView) layoutletter.getChildAt(position);

        textView.setText(Character.toString(letterGuessed));

    }
}

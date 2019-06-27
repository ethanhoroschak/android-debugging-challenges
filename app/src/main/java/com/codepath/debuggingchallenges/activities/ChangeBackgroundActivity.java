package com.codepath.debuggingchallenges.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.codepath.debuggingchallenges.R;

public class ChangeBackgroundActivity extends AppCompatActivity {

    private int oldColor = Color.BLUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);
    }

    public void onGoClick(View view) {
        // Deleted android in front of R
        // Fixed incorrect function call for button, added id to view, made view unclickable and put it behind the button
        View mainView = findViewById(R.id.content);
        mainView.setBackgroundColor(getNextColor());
       // Toast.makeText(ChangeBackgroundActivity.this, "clicked", Toast.LENGTH_LONG).show();
    }

    private int getNextColor() {
        int newColor = (oldColor == Color.BLUE) ? Color.RED : Color.BLUE;
        oldColor = newColor;
        return newColor;
    }
}

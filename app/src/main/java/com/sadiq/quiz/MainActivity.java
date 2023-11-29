package com.sadiq.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sadiq.quiz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static String[] questions = { // array of questions to be used by the layout
            "What is the most popular search engine?",
            "Where does digestion start?",
            "How many planets are there in the solar system?",
            "Which of the following is a fruit?",
            "Most popular browser?",
            "Country with the most people?",
    };

    public static String[][] choices = { // array of arrays of choices to be displayed on the buttons
            {"Google", "Bing", "DuckDuckGo", "Yahoo!"}, // length 4
            {"The stomach", "The intestines", "The mouth", "the liver"},
            {"10", "9", "8", "4"},
            {"Broccoli", "Eggplant", "Cucumber", "Tomato"},
            {"Chrome", "Firefox", "Opera", "Internet Explorer"},
            {"India", "Brazil", "United States", "Egypt"},
    };

    public static String[] correctChoices = {"Google", "The mouth", "8", "Tomato", "Chrome", "India"}; // array of correct choice indices
    ActivityMainBinding mainActivityBinding;
    int points = 0;
    int currQuestion = 0; // index of the current question

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainActivityBinding.getRoot());
        mainActivityBinding.butt1.setOnClickListener(this::onClick);
        mainActivityBinding.butt2.setOnClickListener(this::onClick);
        mainActivityBinding.butt3.setOnClickListener(this::onClick);
        mainActivityBinding.butt4.setOnClickListener(this::onClick);

        nextQuestion();
    }

    public void onClick(View view) {
        Button clicked = (Button) view;
        if (clicked.getText().equals(correctChoices[currQuestion]))
        {
            points++;
            mainActivityBinding.question.setText("Correct!");
        } else {
            mainActivityBinding.question.setText("Incorrect.");
        }
        currQuestion++;
        Handler h =new Handler() ;
        h.postDelayed(new Runnable() {
            public void run() {
                nextQuestion();
            }
        }, 2000);
    }



    public void nextQuestion() {
        mainActivityBinding.title.setText(new StringBuilder().append("Score: ").append(points).toString());
        mainActivityBinding.butt1.setText(choices[currQuestion][0]);
        mainActivityBinding.butt2.setText(choices[currQuestion][1]);
        mainActivityBinding.butt3.setText(choices[currQuestion][2]);
        mainActivityBinding.butt4.setText(choices[currQuestion][3]);
        mainActivityBinding.question.setText(questions[currQuestion]);
    }
}
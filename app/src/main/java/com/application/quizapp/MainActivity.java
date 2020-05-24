package com.application.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionTextView;

    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[]{
            new Question(R.string.question_cyclone, true), //correct: 27
            new Question(R.string.question_fish, false),
            new Question(R.string.question_capital, false),
            new Question(R.string.question_language, true),
            new Question(R.string.question_empire, false),
            new Question(R.string.question_stephen, true),
            new Question(R.string.question_tunnel, false),
            new Question(R.string.question_statue, true),
            new Question(R.string.question_mountain,false),
            new Question(R.string.question_space,false),
            new Question(R.string.question_tech9,false),
            new Question(R.string.question_tech8,true),
            new Question(R.string.question_tech7,true),
            new Question(R.string.question_tech6,false),
            new Question(R.string.question_tech5,false),
            new Question(R.string.question_tech4,true),
            new Question(R.string.question_tech3,false),
            new Question(R.string.question_tech2,true),
            new Question(R.string.question_tech1,true),
            //and add more!
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        questionTextView = findViewById(R.id.textview_question);

        falseButton.setOnClickListener(this); //register our buttons to listen to click events
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        updateQuestion();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:
                //go to next question
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length; // we are safe now!
                updateQuestion();
                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                    updateQuestion();
                }
    }
    }

        private void updateQuestion() {
            Log.d("Current", "onClick: " + currentQuestionIndex);
            questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
        }

        private void checkAnswer(boolean userChooseCorrect) {
            boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
            int toastMessageId;

            if (userChooseCorrect == answerIsTrue) {
                toastMessageId = R.string.correct_answer;
            } else {
                toastMessageId = R.string.wrong_answer;
            }

            Toast.makeText(MainActivity.this, toastMessageId,
                    Toast.LENGTH_SHORT)
                    .show();

        }
}

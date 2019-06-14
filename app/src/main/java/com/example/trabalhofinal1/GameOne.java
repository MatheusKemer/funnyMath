package com.example.trabalhofinal1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameOne extends AppCompatActivity {
    private int round;
    private ArrayList<Integer> done = new ArrayList<Integer>();
    public ArrayList<Integer> options = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    private ArrayList<Integer> quiz = new ArrayList<Integer>();
    public Integer[] allImages = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6, R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10};
    private int generalScore = 0;
    private String currentCorrectAnswer;
    TextView currentRound;
    TextView score;
    Button button1, button2, button3;
    ImageView image;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int index, i;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_one);
        score = findViewById(R.id.scoreValue);
        currentRound = findViewById(R.id.currentRound);

        for (i = 0; i < 5; i++) {
            index = (int)(Math.random() * 10);
            System.out.println("O NUMERO ALEATORIO EH: " + index);
            while (quiz.contains(index)){
                index = (int)(Math.random() * 10);
            }
            quiz.add(index);
        }

        button1 = findViewById(R.id.choice1);
        button2 = findViewById(R.id.choice2);
        button3 = findViewById(R.id.choice3);
        image = findViewById(R.id.imageView);
        System.out.println("QUIZ EH: " + quiz);
        startQuiz();
    }

    private void startQuiz(){
        round = 0;
        score.setText("Placar: 0");
        currentRound.setText("Round 1/5");
        showNewQuestion(round);
    }

    private void showNewQuestion(int currentRound){
        ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        int choice1, choice2;
        int correctAnswer = quiz.get(currentRound);
        temp.remove(correctAnswer);
        currentCorrectAnswer = String.valueOf(correctAnswer+1);
        choice1 = new Random().nextInt(temp.size());
        while (choice1 == correctAnswer){
            choice1 = new Random().nextInt(temp.size());
        }
        temp.remove(choice1);
        choice2 = new Random().nextInt(temp.size());
        while (choice2 == correctAnswer || choice2 == choice1){
            choice2 = new Random().nextInt(temp.size());
        }
        String imageName = "i" + String.valueOf(correctAnswer);
        image.setImageResource(allImages[correctAnswer]);

        button1.setText(String.valueOf(choice1+1));
        button2.setText(String.valueOf(choice2+1));
        button3.setText(String.valueOf(correctAnswer + 1));
    }

    private void nextQuestion(){
        if (round == 4) {
            new AlertDialog.Builder(this)
                    .setTitle("ACABOU!!")
                    .setMessage("Parabéns, sua pontuação foi: " + generalScore)
                    .setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
        } else {
            round = round + 1;
            score.setText("Placar: " + generalScore);
            currentRound.setText("Round " + (round + 1) + "/5");
            showNewQuestion(round);
        }
    }

    public void clickedChoice1(View view){
        wrongAnswer();
        nextQuestion();
    }

    public void clickedChoice2(View view){
        wrongAnswer();
        nextQuestion();
    }

    private void wrongAnswer(){
        new AlertDialog.Builder(this).setTitle("Que pena, errou!").
                setMessage("A resposta certa é " + currentCorrectAnswer)
                .setPositiveButton("Próximo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void clickedChoice3(View view){
        new AlertDialog.Builder(this).setTitle("Acertou!").
                setMessage("Parabéns, a resposta está correta.")
                .setPositiveButton("Próximo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
        generalScore = generalScore + 20;

        nextQuestion();
    }
}

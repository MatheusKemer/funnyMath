package com.example.trabalhofinal1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class GameThree extends AppCompatActivity {
    public ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    private int currentCorrectAnswer;
    private int generalScore = 0;
    public int round = 1;
    EditText answer;
    TextView expression, userRound, totalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_three);

        answer = findViewById(R.id.answer);
        expression = findViewById(R.id.expression);
        totalScore = findViewById(R.id.scoreValue);
        userRound = findViewById(R.id.currentRound);

        startGame();
    }

    public void startGame(){
        round = 1;
        generalScore = 0;
        showNextQuestion(round);
    }

    private void showNextQuestion(int round){
        totalScore.setText("Placar: " + generalScore);
        userRound.setText("Rodada: " + round + "/5");

        if (round == 6) {
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
            String newExpression = getRandomExpression();

            expression.setText(newExpression);
        }
    }

    private String getRandomExpression(){
        int number;
        ArrayList<Integer> maxNumber = new ArrayList<Integer>();
        int maxPossibleNumber;
        String newExpression = "";
        for (int i = 0; i < 3; i++){
            number = numbers.get((int)(Math.random() * 10));
            maxNumber.add(number);
            newExpression = newExpression + " " + number;
        }
        Collections.sort(maxNumber, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();

        for (int n : maxNumber){
            sb.append(n);
        }

        currentCorrectAnswer = Integer.valueOf(sb.toString());

        return newExpression;
    }

    public void sendAnswer(View view){
        if (answer.getText().length() == 0){
            new AlertDialog.Builder(this).setTitle("Erro!").
                    setMessage("Preencha um valor.").show();
        } else {
            int intAnswer = Integer.valueOf(answer.getText().toString());
            round = round + 1;
            answer.setText("");

            if (intAnswer == currentCorrectAnswer) {
                correctAnswer();
            } else {
                wrongAnswer();
            }
        }
    }

    private void correctAnswer(){
        new AlertDialog.Builder(this).setTitle("Acertou!").
                setMessage("Parabéns, a resposta está correta.")
                .setPositiveButton("Próximo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
        generalScore = generalScore + 20;
        showNextQuestion(round);
    }

    private void wrongAnswer(){
        new AlertDialog.Builder(this).setTitle("Que pena, errou!").
                setMessage("A resposta certa é " + currentCorrectAnswer)
                .setPositiveButton("Próximo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
        showNextQuestion(round);
    }
}

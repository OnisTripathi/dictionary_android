package com.example.hw3_v3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
//import android.support.v4.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    globalVariable global = globalVariable.getInstance();

    //like name implies
    Button homePage;
    Button clear;
    Button add;

    HashMap<String, Integer> wordToFrequency;
    HashMap<String, String> wordToDefinition;

    TextView test;


    EditText inputWord;
    EditText inputFrequency;
    EditText inputDefinition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        wordToFrequency = global.getWordToFrequency();
        wordToDefinition = global.getWordToDefinition();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        test=findViewById(R.id.textView1);

//        //declare input text
        inputWord = findViewById(R.id.editTextTextPersonName3);
        inputFrequency = findViewById(R.id.editTextTextPersonName8);
        inputDefinition = findViewById(R.id.displayDefinition);

        //declare buttons
        homePage = findViewById(R.id.button1);
        clear = findViewById(R.id.button2);
        add = findViewById(R.id.button);


        clear.setOnClickListener(v -> clearButton());
        homePage.setOnClickListener(v -> openHomePage());

//        add.setOnClickListener(view -> {
//            String word = inputWord.toString() ;
//
//            String frequencySTR = inputFrequency.toString();
//            int frequency = Integer.parseInt(frequencySTR);
//
//            String definition = inputDefinition.toString();
//
//            wordToFrequency.put(word, frequency);
//            wordToDefinition.put(word, definition);
//
//            global.setWordToDefinition(wordToDefinition);
//            global.setWordToFrequency(wordToFrequency);
//            clearButton();
//        });

//        String word;
//        add.setOnClickListener(view ->{
//                String word = inputWord.toString();
//
////                String frequencySTR = inputFrequency.toString();
////                Integer frequency = Integer.parseInt(frequencySTR);
//
//                String definition = inputDefinition.toString();
//                wordToFrequency.put(word, 50);
//                wordToDefinition.put(word, definition);
//
//                global.setWordToDefinition(wordToDefinition);
//                global.setWordToFrequency(wordToFrequency);
//        });

        add.setOnClickListener(v -> addButton());
    }

    public void openHomePage() {
        finish();
    }

    public void clearButton() {
//        test.setText(wordToDefinition.get("ProLife"));
        inputWord.getText().clear();
        inputFrequency.getText().clear();
        inputDefinition.getText().clear();
    }

    public void addButton() {
        String word = inputWord.getText().toString();

        String frequencySTR = inputFrequency.getText().toString();
        int frequency = Integer.parseInt(frequencySTR);

        String definition = inputDefinition.getText().toString();

        wordToFrequency.put(word, frequency);
        wordToDefinition.put(word, definition);
    }

}
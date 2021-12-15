package com.example.hw3_v3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;


public class MainActivity extends AppCompatActivity{

    globalVariable global = globalVariable.getInstance();

    //like name implies
    Button newWord;
    Button removeWord;
    TextView displayDefinition;
    TextView frequency1;
    TextView frequency2;
    TextView frequency3;
    EditText inputWord;
    Button findWord;
    HashMap<String, Integer> wordToFrequency;
    HashMap<String, String> wordToDefinition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        wordToFrequency = global.getWordToFrequency();
        wordToDefinition = global.getWordToDefinition();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        frequency1=findViewById(R.id.editTextTextPersonName7);
        frequency2=findViewById(R.id.editTextTextPersonName6);
        frequency3=findViewById(R.id.editTextTextPersonName5);


        //declare button to go back and forth
        newWord = findViewById(R.id.button2);
        removeWord = findViewById(R.id.button1);
        inputWord = findViewById(R.id.inputWord);

        //search for word
        findWord = findViewById(R.id.button3);

        //check to switch screen or not
        removeWord.setOnClickListener(view -> removeWord());
        newWord.setOnClickListener(v -> openNewWord());

        displayDefinition= findViewById(R.id.displayDefinition);

        //check to see if
        findWord.setOnClickListener(v -> {
            frequency1.setText("");
            frequency2.setText("");
            frequency3.setText("");

            if(inputWord.getText().toString().isEmpty()){
                displayDefinition.setText("");
                Toast.makeText(MainActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();
            }else{
                //inputted word
                String display = inputWord.getText().toString();

                //is word in dictionary?
                if(checkIfWordInDictionary(display)){
                    frequency1.setText(display);
                    //display word
                    if (wordToDefinition.containsKey(display)){
                        String definition = wordToDefinition.get(display);
                        displayDefinition.setText(definition);
                    }
                }else{
                    displayDefinition.setText("");
                    findSimilarWord(display);
                }

            }
        });
    }

    public void clearField(){
        frequency1.setText("");
        frequency2.setText("");
        frequency3.setText("");
        inputWord.getText().clear();
        displayDefinition.setText("");
    }

    public void removeWord(){

        //word to remove
        String value;
        value = inputWord.getText().toString();

        //error check
        if(value.isEmpty()){
            clearField();
            Toast.makeText(MainActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();
        }
        boolean checkWord = checkIfWordInDictionary(value);
        if(!checkWord){
            clearField();
            Toast.makeText(MainActivity.this, "Word does not exist", Toast.LENGTH_SHORT).show();
        }


        //clear screens
        clearField();

        boolean booleanDefinition = false;
        boolean booleanFrequency = false;

        //remove from dictionary hashmap
        for (String key : wordToDefinition.keySet()) {
            if (key.equals(value)) {
                booleanDefinition = true;
                break;
            }
        }
        if(booleanDefinition) {
            wordToDefinition.remove(value);
        }
        //remove from frequency hashmap
        for (String key : wordToFrequency.keySet()) {
            if (key.equals(value)) {
                booleanFrequency = true;
                break;
            }
        }

        if(booleanFrequency) {
            wordToFrequency.remove(value);
        }
    }

    public void openNewWord() {

        global.setWordToDefinition(wordToDefinition);
        global.setWordToFrequency(wordToFrequency);

        Intent myIntent = new Intent(MainActivity.this, Activity2.class);
        startActivity(myIntent);
    }

    public boolean checkIfWordInDictionary(String value){
        boolean found = false;
        for(String key : wordToDefinition.keySet())
        {
            if(key.equals(value))
            {
                found = true;
                break;
            }
        }
        return found;
    }

    public void findSimilarWord(String value){

        HashMap<String, Integer> similarWord = new HashMap<>();
//        ArrayList<String> similarWord = new ArrayList<>();

        for(String key : wordToDefinition.keySet())
        {
            if(key.contains(value))
            {
                similarWord.put(key, wordToFrequency.get(key));
            }
        }

        //sort hashmap by value
        sortByValue(similarWord);

    }


    public void sortByValue(HashMap<String, Integer> hm) {
        frequency1=findViewById(R.id.editTextTextPersonName7);
        frequency2=findViewById(R.id.editTextTextPersonName6);
        frequency3=findViewById(R.id.editTextTextPersonName5);
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        //max we have

        //put ordered key into an array list
        ArrayList<String> similarWordCount = new ArrayList<>(temp.keySet());

        //print out top 3(have the highest value)
        String frequent1 = similarWordCount.get(0);
        String frequent2 = similarWordCount.get(1);
        String frequent3 = similarWordCount.get(2);

        frequency1.setText(frequent1);
        frequency2.setText(frequent2);
        frequency3.setText(frequent3);
    }

}
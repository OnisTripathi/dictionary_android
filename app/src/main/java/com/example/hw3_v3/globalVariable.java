package com.example.hw3_v3;

import java.util.HashMap;

public class globalVariable {

    //keep track of
    private static HashMap<String, Integer> wordToFrequency = new HashMap<String, Integer>(){{
        put("Programming", 100);
        put("Process", 350);
        put("Progressive", 500);
        put("Productive", 200);
    }};

    private static HashMap<String, String> wordToDefinition = new HashMap<String, String>(){{
        put("Programming", "the process or activity of writing computer programs.");
        put("Process", "a series of actions or steps taken in order to achieve a particular end.");
        put("Progressive", "happening or developing gradually or in stages; proceeding step by step.");
        put("Productive", "producing or able to produce large amounts of goods, crops, or other commodities.");
    }};

    private static globalVariable instance;

//    public static void addInfo(String word, String frequencySTR, String definition) {
//        wordToFrequency.put(word, frequency);
//        wordToDefinition.put(word, definition);
//    }

    public HashMap<String, Integer> getWordToFrequency() {
        return wordToFrequency;
    }

    public HashMap<String, String> getWordToDefinition() {
        return wordToDefinition;
    }

    public void setWordToDefinition(HashMap<String, String> wordToDefinition) {
        this.wordToDefinition = wordToDefinition;
    }

    public void setWordToFrequency(HashMap<String, Integer> wordToFrequency) {
        this.wordToFrequency = wordToFrequency;
    }

    public static globalVariable getInstance() {
        if (instance==null){
            instance=new globalVariable();
        }
        return instance;
    }

}

package com.example.puneetchugh.myapplication;

/**
 * Created by puneetchugh on 6/25/16.
 */
public class QuizQuestion {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;

    public QuizQuestion(String question, String optionA, String optionB, String optionC, String optionD, String answer){

        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public String getOptionA(){
        return optionA;
    }

    public String getOptionB(){
        return optionC;
    }

    public String getOptionC(){
        return optionD;
    }

    public String getAnswer(){
        return answer;
    }
}

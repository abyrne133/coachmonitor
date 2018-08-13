package models;

import java.util.ArrayList;
import java.util.List;

public class Question {
    public String title;
    public String answer;
    public String answerId;
    public String questionType;
    public String slideId;
    public Integer slideCurrentValue;
    public Integer slideMaxValue;
    public Integer slideMinValue;
    public Integer slideDefaultValue;

    public static List<Question> questions;

    //persist in db later
    static{
        questions = new ArrayList<>();
        Question q = new Question("Stress level?", "Slider");
        q.slideMinValue = 1;
        q.slideMaxValue = 10;
        q.slideDefaultValue = 5;
        q.slideId = "stressLevel";
        q.answerId = "stressLevelAnswer";
        questions.add(q);
    }
    public Question(String title, String questionType){
        this.title = title;
        this.questionType = questionType;
    }
}

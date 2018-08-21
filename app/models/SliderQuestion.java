package models;

import javax.persistence.*;

@Entity
public class SliderQuestion extends Question {
    public Integer currentValue;
    public Integer maxValue;
    public Integer minValue;
    public Integer defaultValue;

    public SliderQuestion(String title, String questionType){
        super(title, questionType);
        this.maxValue = 10;
        this.minValue = 1;
        this.defaultValue = 5;
    }
}

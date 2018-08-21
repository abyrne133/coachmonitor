package models;

import javax.persistence.Entity;

@Entity
public class StandardQuestion extends Question {
    public StandardQuestion(String title, String questionType){
        super(title, questionType);
    }
}

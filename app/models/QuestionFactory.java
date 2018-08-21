package models;

public class QuestionFactory {
    public Question getQuestion(String title,String questionType){
        if(questionType == null){
            return null;
        }
        if(questionType.equalsIgnoreCase("slider")){
            return new SliderQuestion(title, questionType);
        } else if(questionType.equalsIgnoreCase("standard")){
            return new StandardQuestion(title, questionType);
        }
        return null;
    }
}

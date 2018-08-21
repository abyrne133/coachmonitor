package models;

public class QuestionFactory {
    public static Question getQuestion(String title,String questionType){
        if(questionType == null){
            return null;
        }
        if(questionType.equalsIgnoreCase("slider")){
            return new SliderQuestion(title, questionType);
        } else {
            return new Question(title, questionType);
        }
    }
}

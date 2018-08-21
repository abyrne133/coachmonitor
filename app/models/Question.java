package models;

import com.avaje.ebean.Model;
import javax.persistence.*;

@Entity(name="Question")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="QuestionDiscriminator")
public class Question extends Model {
    @Id
    public Long id;
    public String title;
    public String questionType;

    @OneToOne
    public Answer answer;

    public Question(String title, String questionType) {
        this.title = title;
        this.questionType = questionType;
    }

    public static Finder<Long, Question> find = new Finder<>(Question.class);

    public static Question findById(Long Id){
        return find.byId(Id);
    }

}

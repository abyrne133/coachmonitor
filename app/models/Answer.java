package models;

import com.avaje.ebean.Model;
import javax.persistence.*;

@Entity
public class Answer extends Model {
    @Id
    public Long Id;
    public String answerText;
    public Question question;
}

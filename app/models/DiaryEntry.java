package models;

import com.avaje.ebean.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class DiaryEntry extends Model {
    @Id
    public Long id;

    public LocalDateTime dateTime;

    @OneToOne
    public List<Question> questions;

    @ManyToOne
    public User user;

    public Integer overallScore;

    public Integer calculateScore(){
        return 0;
    }

    public DiaryEntry(){

    }

    public static Finder<Long, DiaryEntry>  find = new Finder<>(DiaryEntry.class);
    public static DiaryEntry findById(Long Id){
        return find.byId(Id);
    }

}

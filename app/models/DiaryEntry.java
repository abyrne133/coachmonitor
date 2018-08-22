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
    public String stress;
    public Boolean legs;
    public Boolean arms;

    @ManyToOne
    public User user;


    public DiaryEntry(){
    }

    public static Finder<Long, DiaryEntry>  find = new Finder<>(DiaryEntry.class);

}

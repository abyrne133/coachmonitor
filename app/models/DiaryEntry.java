package models;

import com.avaje.ebean.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Entity
public class DiaryEntry extends Model {
    @Id
    public Long id;
    public String submittedOn;
    public Integer stress;
    public Integer mood;
    public String legs;
    public String arms;
    public String back;
    public String comment;

    @ManyToOne
    public User user;

    public DiaryEntry(){}

    public void calcDateTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM YYYY, HH:mm:ss");
        this.submittedOn = now.format(formatter);
    };

    public static Finder<Long, DiaryEntry>  find = new Finder<>(DiaryEntry.class);

}

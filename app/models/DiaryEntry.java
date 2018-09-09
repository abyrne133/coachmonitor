package models;

import com.avaje.ebean.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Entity
public class DiaryEntry extends Model {
    @Id
    public Long id;
    public String submittedOn;
    public Integer stress;
    public Integer nutrition;
    public Integer mood;
    public Integer sleep;
    public Integer muscles;

    public String legs;
    public String chest;
    public String arms;
    public String back;
    public String feet;
    public String neck;
    public String shoulder;
    public String other;
    public String comment;
    public String coachComment;

    @ManyToOne
    public User user;

    public DiaryEntry(){}

    public void calcDateTime(){
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM YYYY");
        this.submittedOn = now.format(formatter);
    };

    public void setDefaultSlides(){
        this.stress = 5;
        this.mood = 5;
        this.sleep = 5;
        this.muscles = 5;
        this.nutrition = 5;
    };

    public static Finder<Long, DiaryEntry>  find = new Finder<>(DiaryEntry.class);

    public static List<DiaryEntry> getPage(int pageNo, int pageSize, String userMail, Boolean isAdmin) {
        final int endRow = (pageNo * pageSize);
        final int startRow = endRow - pageSize;
        if (isAdmin) {
            return DiaryEntry.find.setFirstRow(startRow).setMaxRows(pageSize).orderBy("id desc").findPagedList().getList();
        }

        return DiaryEntry.find.where().eq("user.email", userMail).setFirstRow(startRow).setMaxRows(pageSize).orderBy("id desc").findPagedList().getList();

    }

    public static List<DiaryEntry> getPagePerAthlete(int pageNo, int pageSize, String athleteMail) {
        final int endRow = (pageNo * pageSize);
        final int startRow = endRow - pageSize;
        return DiaryEntry.find.where().eq("user.email", athleteMail).setFirstRow(startRow).setMaxRows(pageSize).orderBy("id desc").findPagedList().getList();
    }

    public static int getTotalPages(int pageSize, String userMail, Boolean isAdmin){
        if (isAdmin){
            return ((DiaryEntry.find.findCount()+1)/pageSize);
        }
        return ((DiaryEntry.find.where().eq("user.email",userMail).findCount()+1)/pageSize);
    }

    public static int getTotalPagesPerAthlete(int pageSize, String athleteMail){
        return ((DiaryEntry.find.where().eq("user.email",athleteMail).findCount()+1)/pageSize);
    }
}

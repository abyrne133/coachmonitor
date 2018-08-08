package models;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiaryEntry {
    public Integer id;
    public LocalDateTime dateTime;
    public List<Question> questions;
    public String athleteName;
    public Integer overallScore;

    public Integer calculateScore(){
        return 0;
    }

    public DiaryEntry(){};

    public DiaryEntry(Integer id, LocalDateTime dateTime, String athleteName){
        this.id = id;
        this.dateTime = dateTime;
        this.athleteName = athleteName;
    }
    private static Set<DiaryEntry> diaryEntries;
    static{
        diaryEntries = new HashSet<>();
        diaryEntries.add(new DiaryEntry(1,LocalDateTime.now(), "John Smith"));
        diaryEntries.add(new DiaryEntry(2,LocalDateTime.now(), "Mary Smith"));

    }

    public static Set<DiaryEntry> allDiaryEntry(){
        return diaryEntries;
    }

    public static DiaryEntry findById(Integer id){
        for(DiaryEntry diaryEntry : diaryEntries){
            if(id.equals(diaryEntry.id)){
                return diaryEntry;
            }
        }
        return null;
    }

    public static void add(DiaryEntry diaryEntry){
        diaryEntries.add(diaryEntry);
    }

    public static boolean remove(DiaryEntry diaryEntry){
        return diaryEntries.remove(diaryEntry);
    }
}

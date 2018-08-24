package controllers;
import com.avaje.ebean.Finder;
import models.DiaryEntry;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.diaryEntry.*;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DiaryEntryController extends Controller{

    @Inject
    FormFactory formFactory;

    public Result index(){
        List<DiaryEntry> diaryEntries = DiaryEntry.find.all();
        return ok(index.render(diaryEntries));
    }

    public Result create(){
        DiaryEntry diaryEntry = new DiaryEntry();
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class);
        return ok(create.render(diaryEntryForm,"Journal Entry"));
    }

    public Result save(){
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).bindFromRequest();
        DiaryEntry diaryEntry =  diaryEntryForm.get();
        diaryEntry.calcDateTime();
        diaryEntry.save();
        return redirect(routes.DiaryEntryController.index());
    }

    public Result edit(Long id){
        return TODO;
    }

    public Result update(){
        return TODO;
    }

    public Result destroy(Long id){
        return TODO;
    }

    public Result show(Long id){
        DiaryEntry diaryEntry = DiaryEntry.find.byId(id);
        return ok(show.render(diaryEntry));
    }

}
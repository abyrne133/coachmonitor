package controllers;
import models.DiaryEntry;
import models.Question;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.diaryEntry.*;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Set;

public class DiaryEntryController extends Controller{

    @Inject
    FormFactory formFactory;

    public Result index(){
        Set<DiaryEntry> diaryEntries = DiaryEntry.allDiaryEntry();
        return ok(index.render(diaryEntries));
    }

    public Result create(){
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class);
        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.questions = Question.questions;
        return ok(create.render(diaryEntryForm, diaryEntry));
    }

    public Result save(){
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).bindFromRequest();
        DiaryEntry diaryEntry =  diaryEntryForm.get();
        DiaryEntry.add(diaryEntry);
        return redirect(routes.DiaryEntryController.index());
    }

    public Result edit(Integer id){
        return TODO;
    }

    public Result update(){
        return TODO;
    }

    public Result destroy(Integer id){
        return TODO;
    }

    public Result show(Integer id){
        return TODO;
    }
}

package controllers;
import com.avaje.ebean.Finder;
import models.DiaryEntry;
import models.Question;
import models.QuestionFactory;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.diaryEntry.*;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DiaryEntryController extends Controller{

    @Inject
    FormFactory formFactory;

    public Result index(){
        List<DiaryEntry> diaryEntries = DiaryEntry.find.findList();
        return ok(index.render(diaryEntries));
    }

    public Result create(){
        DiaryEntry diaryEntry = new DiaryEntry();
        List<Question> defaultQuestions = new ArrayList<>();
        defaultQuestions.add(QuestionFactory.getQuestion("How old are you?", "standard"));
        defaultQuestions.add(QuestionFactory.getQuestion("Stress Level?", "slider"));
        defaultQuestions.add(QuestionFactory.getQuestion("Would you walk on the beach?", "standard"));
        diaryEntry.questions = defaultQuestions;
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).fill(diaryEntry);
        return ok(create.render(diaryEntryForm,"Journal Entry"));
    }

    public Result save(){
        return TODO;
        /**
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).bindFromRequest();
        DiaryEntry diaryEntry =  diaryEntryForm.get();
        DiaryEntry.add(diaryEntry);
        return redirect(routes.DiaryEntryController.index());
         */
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
        DiaryEntry diaryEntry = DiaryEntry.findById(id);
        return ok(show.render(diaryEntry));
    }

}

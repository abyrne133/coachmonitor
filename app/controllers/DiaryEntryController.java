package controllers;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.avaje.ebean.Finder;
import com.feth.play.module.pa.PlayAuthenticate;
import models.DiaryEntry;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import service.UserProvider;
import views.html.diaryEntry.*;
import javax.inject.Inject;
import java.text.Normalizer;
import java.util.Collections;
import java.util.List;
@Restrict(@Group(Application.USER_ROLE))
public class DiaryEntryController extends Controller{

    @Inject
    FormFactory formFactory;

    public Result index(){
        List<DiaryEntry> diaryEntries = DiaryEntry.find.all();
        return ok(index.render(diaryEntries));
    }

    public Result create(){
        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.calcDateTime();
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).fill(diaryEntry);
        return ok(create.render(diaryEntryForm));
    }

    public Result save(){
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).bindFromRequest();
        DiaryEntry diaryEntry =  diaryEntryForm.get();
        //final User localUser = userProvider.getUser(session());
        //diaryEntry.user = localUser;
        diaryEntry.save();
        return redirect(routes.DiaryEntryController.index());
    }

    public Result edit(Long id){
        DiaryEntry diaryEntry = DiaryEntry.find.byId((id));
        if(diaryEntry == null){
            return notFound("You are attempting to edit a dairy entry that does not exist.");
        }
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).fill(diaryEntry);
        return ok(update.render(diaryEntryForm));
    }

    public Result update(){
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).bindFromRequest();
        DiaryEntry diaryEntry =  diaryEntryForm.get();
        diaryEntry.update();
        return redirect(routes.DiaryEntryController.index());
    }

    public Result delete(Long id){
        DiaryEntry diaryEntry = DiaryEntry.find.byId((id));
        if(diaryEntry == null){
            return notFound("You are attempting to delete a dairy entry that does not exist.");
        }
        diaryEntry.delete();
        return redirect(routes.DiaryEntryController.index());
    }

    public Result show(Long id){
        DiaryEntry diaryEntry = DiaryEntry.find.byId(id);
        if(diaryEntry ==null){
            return notFound("You are attempting to show a dairy entry that does not exist.");
        }
        return ok(show.render(diaryEntry));
    }

}
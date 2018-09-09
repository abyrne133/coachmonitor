package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import com.feth.play.module.pa.PlayAuthenticate;
import models.DiaryEntry;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import providers.MyUsernamePasswordAuthProvider;
import providers.MyUsernamePasswordAuthProvider.MyLogin;
import providers.MyUsernamePasswordAuthProvider.MySignup;
import service.UserAgent;
import service.UserProvider;
import views.html.*;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application extends Controller {
	public static final String FLASH_MESSAGE_KEY = "message";
	public static final String FLASH_ERROR_KEY = "error";
	public static final String USER_ROLE = "user";
	private final PlayAuthenticate auth;
	private final MyUsernamePasswordAuthProvider provider;
	private final FormFactory formFactory;
	private final UserProvider userProvider;
	public static String formatTimestamp(final long t) {
		return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
	}

	@Inject
	public Application(final PlayAuthenticate auth, final MyUsernamePasswordAuthProvider provider,
					   final UserProvider userProvider, final FormFactory formFactory) {
		this.auth = auth;
		this.provider = provider;
		this.userProvider = userProvider;
		this.formFactory = formFactory;
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result index(int pageNo){
		final User localUser = userProvider.getUser(session());
		final String userMail = localUser.email;
		final String userName = localUser.name;
		boolean isAdmin = localUser.isAdmin();
		List<User> userEmailAndNames = new ArrayList<>();
		if (isAdmin){
			userEmailAndNames = User.getUserEmailAndNameOnly();
		}

		final int pageSize = 10;
		List<DiaryEntry> diaryEntries = DiaryEntry.getPage(pageNo, pageSize, userMail, isAdmin);
		final int totalPages = DiaryEntry.getTotalPages(pageSize, userMail, isAdmin);
		final UserAgent userAgent = new UserAgent(request());
		if (userAgent.isMobileDevice()){
            return ok(mobileIndex.render(this.userProvider, diaryEntries, userName, userMail, pageNo , totalPages, isAdmin, userEmailAndNames));
        }
		return ok(index.render(this.userProvider, diaryEntries, userName, userMail, pageNo , totalPages, isAdmin, userEmailAndNames));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result getAthleteJournalEntries(String athleteMail,int pageNo){
		final User localUser = userProvider.getUser(session());
		final String userMail = localUser.email;
		final String userName = localUser.name;
		final int pageSize = 10;
		List<DiaryEntry> diaryEntries = DiaryEntry.getPagePerAthlete(pageNo, pageSize, athleteMail);
		final int totalPages = DiaryEntry.getTotalPagesPerAthlete(pageSize, athleteMail);
		final UserAgent userAgent = new UserAgent(request());
		if (userAgent.isMobileDevice()){
			return TODO;
		}
		return ok(_desktopPartial.render(diaryEntries, userName, userMail, pageNo , totalPages));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result create(){
		DiaryEntry diaryEntry = new DiaryEntry();
		diaryEntry.calcDateTime();
		diaryEntry.setDefaultSlides();
		Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).fill(diaryEntry);
		return ok(create.render(this.userProvider, diaryEntryForm));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result save(){
		Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).bindFromRequest();
		DiaryEntry diaryEntry =  diaryEntryForm.get();
		final User localUser = userProvider.getUser(session());
		diaryEntry.user = localUser;
		diaryEntry.save();
		return redirect(routes.Application.index(1));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result show(Long id){
		DiaryEntry diaryEntry = DiaryEntry.find.byId(id);
		if(diaryEntry ==null){
			return notFound("You are attempting to show a dairy entry that does not exist.");
		}
		return ok(show.render(this.userProvider, diaryEntry));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result edit(Long id){
		DiaryEntry diaryEntry = DiaryEntry.find.byId((id));
		if(diaryEntry == null){
			return notFound("You are attempting to edit a dairy entry that does not exist.");
		}
		final User localUser = userProvider.getUser(session());
		if(localUser.email.equalsIgnoreCase(diaryEntry.user.email)) {
			Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).fill(diaryEntry);
			return ok(update.render(this.userProvider, diaryEntryForm));
		}
		return redirect(routes.Application.index(1));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result update(){
		Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).bindFromRequest();
		DiaryEntry diaryEntry =  diaryEntryForm.get();
		diaryEntry.update();
		return redirect(routes.Application.index(1));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result delete(Long id){
		DiaryEntry diaryEntry = DiaryEntry.find.byId((id));
		if(diaryEntry == null){
			return notFound("You are attempting to delete a dairy entry that does not exist.");
		}
		diaryEntry.delete();
		return redirect(routes.Application.index(1));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result provideFeedback(Long id){
        DiaryEntry diaryEntry = DiaryEntry.find.byId(id);
        if(diaryEntry ==null){
            return notFound("You are attempting to provide feedback on a dairy entry that does not exist.");
        }
        Form<DiaryEntry> diaryEntryForm = formFactory.form(DiaryEntry.class).fill(diaryEntry);
        return ok(feedback.render(this.userProvider, diaryEntryForm));
	}

	@Restrict(@Group(Application.USER_ROLE))
	public Result profile() {
		final User localUser = userProvider.getUser(session());
		return ok(profile.render(this.auth, this.userProvider, localUser));
	}

	public Result login() {
		return ok(login.render(this.auth, this.userProvider,  this.provider.getLoginForm()));
	}

	public Result doLogin() {
		com.feth.play.module.pa.controllers.Authenticate.noCache(response());
		final Form<MyLogin> filledForm = this.provider.getLoginForm()
				.bindFromRequest();
		if (filledForm.hasErrors()) {
			// User did not fill everything properly
			return badRequest(login.render(this.auth, this.userProvider, filledForm));
		} else {
			// Everything was filled
			return this.provider.handleLogin(ctx());
		}
	}

	public Result signup() {
		return ok(signup.render(this.auth, this.userProvider, this.provider.getSignupForm()));
	}

	public Result jsRoutes() {
		return ok(
				play.routing.JavaScriptReverseRouter.create("jsRoutes",
						routes.javascript.Signup.forgotPassword()))
				.as("text/javascript");

	}

	public Result doSignup() {
		com.feth.play.module.pa.controllers.Authenticate.noCache(response());
		final Form<MySignup> filledForm = this.provider.getSignupForm().bindFromRequest();
		if (filledForm.hasErrors()) {
			// User did not fill everything properly
			return badRequest(signup.render(this.auth, this.userProvider, filledForm));
		} else {
			// Everything was filled
			// do something with your part of the form before handling the user
			// signup
			return this.provider.handleSignup(ctx());
		}
	}
}
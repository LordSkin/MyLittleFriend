package kramnik.bartlomiej.mylittlefriend.Root.Dagger;

import dagger.Component;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseServer;
import kramnik.bartlomiej.mylittlefriend.Model.Services.CheckingAgentsService;
import kramnik.bartlomiej.mylittlefriend.Presenter.ShowObservationsPresenter;
import kramnik.bartlomiej.mylittlefriend.View.Dialogs.AddAgentDialog;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.ListAdapter.AgentsListAdapter;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.SelectAgentActivity;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.SelectAgentView;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsActivity;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsView;
import kramnik.bartlomiej.mylittlefriend.View.ViewObservations.ShowObservationsView;

/**
 * Created by Mao on 04.02.2018.
 */

@Component(modules = AppModule.class)
public interface AppComponent {

    public void inject(SelectAgentActivity view);

    public void inject(SendCommandsActivity view);

    public void inject(ResponseServer server);

    public void inject(AgentsListAdapter adapter);

    public void inject(AddAgentDialog addAgentDialog);

    public void inject(CheckingAgentsService service);

    public void inject(ShowObservationsView view);
}

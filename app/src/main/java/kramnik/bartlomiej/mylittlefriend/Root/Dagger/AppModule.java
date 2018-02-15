package kramnik.bartlomiej.mylittlefriend.Root.Dagger;

import dagger.Module;
import dagger.Provides;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseListener;
import kramnik.bartlomiej.mylittlefriend.Model.Services.ServiceDataProvider;
import kramnik.bartlomiej.mylittlefriend.Presenter.AppPresenter;
import kramnik.bartlomiej.mylittlefriend.Presenter.SelectAgentPresenter;
import kramnik.bartlomiej.mylittlefriend.Presenter.SendCommandPresenter;
import kramnik.bartlomiej.mylittlefriend.Presenter.ShowObservationsPresenter;
import kramnik.bartlomiej.mylittlefriend.View.Dialogs.AddAgent;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.ListAdapter.AgentsAdapter;

/**
 * Created by Mao on 04.02.2018.
 */

@Module
public class AppModule {

    private AppPresenter presenter;

    public AppModule(AppPresenter presenter) {
        this.presenter = presenter;
    }

    @Provides
    public SelectAgentPresenter provideSelectAgentPresenter(){
        return  presenter;
    }

    @Provides
    public SendCommandPresenter provideSendCommandPresenter(){
        return  presenter;
    }

    @Provides
    public ResponseListener provideResponseListener(){
        return  presenter;
    }

    @Provides
    public AgentsAdapter provideAgentsAdapter(){
        return presenter;
    }

    @Provides
    public AddAgent provideAddAgent(){
        return presenter;
    }

    @Provides
    public ServiceDataProvider provideServiceInject(){
        return  presenter;
    }

    @Provides
    public ShowObservationsPresenter provideShowObservations(){
        return  presenter;
    }
}

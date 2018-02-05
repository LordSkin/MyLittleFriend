package kramnik.bartlomiej.mylittlefriend.Root.Dagger;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseListener;
import kramnik.bartlomiej.mylittlefriend.Presenter.AppPresenter;
import kramnik.bartlomiej.mylittlefriend.Presenter.SelectAgentPresenter;
import kramnik.bartlomiej.mylittlefriend.Presenter.SendCommandPresenter;

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
}

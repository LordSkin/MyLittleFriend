package kramnik.bartlomiej.mylittlefriend.Root.Dagger;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
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

    @Singleton
    @Provides
    public SelectAgentPresenter provideSelectAgentPresenter(){
        return  presenter;
    }

    @Singleton
    @Provides
    public SendCommandPresenter provideSendCommandPresenter(){
        return  presenter;
    }
}

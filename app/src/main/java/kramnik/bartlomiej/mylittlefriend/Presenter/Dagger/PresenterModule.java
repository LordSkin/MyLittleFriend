package kramnik.bartlomiej.mylittlefriend.Presenter.Dagger;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsDataBase;
import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsList;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseServer;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.AgentConnector;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;
import kramnik.bartlomiej.mylittlefriend.Root.App;
import okhttp3.Response;

/**
 * Created by Mao on 04.02.2018.
 */

@Module
public class PresenterModule {
    private App app;
    private AgentConnector sender;
    private AgentsList agentsList;

    public PresenterModule(final App app) {
        this.app = app;
        sender = new RequestSender();
        agentsList = new AgentsList(app);
    }

    @Provides
    public Context provideContext(){
        return app;
    }


    @Provides
    public AgentConnector provideSender(){
        return sender;
    }

    @Provides
    public AgentsList provideAgentsList(){
        return agentsList;
    }
}

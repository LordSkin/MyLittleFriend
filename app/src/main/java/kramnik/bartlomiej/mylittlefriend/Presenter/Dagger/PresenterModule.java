package kramnik.bartlomiej.mylittlefriend.Presenter.Dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsList;
import kramnik.bartlomiej.mylittlefriend.Model.Notifications.NotificationsMenager;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.AgentConnector;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;
import kramnik.bartlomiej.mylittlefriend.Root.App;

/**
 * Created by Mao on 04.02.2018.
 */

@Module
public class PresenterModule {
    private App app;
    private AgentConnector sender;
    private AgentsList agentsList;
    private NotificationsMenager notifications;

    public PresenterModule(final App app) {
        this.app = app;
        sender = new RequestSender();
        agentsList = new AgentsList(app);
        notifications = new NotificationsMenager(app);
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

    @Provides
    public NotificationsMenager provideNotifications(){
        return notifications;
    }
}

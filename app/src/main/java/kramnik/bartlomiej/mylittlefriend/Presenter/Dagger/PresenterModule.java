package kramnik.bartlomiej.mylittlefriend.Presenter.Dagger;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsDataBase;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseServer;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;
import kramnik.bartlomiej.mylittlefriend.Root.App;
import okhttp3.Response;

/**
 * Created by Mao on 04.02.2018.
 */

@Module
public class PresenterModule {
    private App app;
    private RequestSender sender;
    private AgentsDataBase dataBase;

    public PresenterModule(final App app) {
        this.app = app;
        sender = new RequestSender();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                dataBase = Room.databaseBuilder(app, AgentsDataBase.class, "bazaDanych1").build();
                return null;
            }
        }.execute();
    }

    @Provides
    public Context provideContext(){
        return app;
    }


    @Provides
    public RequestSender provideSender(){
        return sender;
    }

    @Provides
    public AgentsDataBase provideDataBase(){
        return dataBase;
    }
}

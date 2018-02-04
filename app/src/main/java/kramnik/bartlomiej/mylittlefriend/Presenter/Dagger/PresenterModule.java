package kramnik.bartlomiej.mylittlefriend.Presenter.Dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
    private ResponseServer responseServer;
    private RequestSender sender;

    public PresenterModule(App app) {
        this.app = app;
        responseServer = new ResponseServer();
        sender = new RequestSender();
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return app;
    }

    @Provides
    @Singleton
    public ResponseServer provideServer(){
        return responseServer;
    }

    @Provides
    @Singleton
    public RequestSender provideSender(){
        return sender;
    }
}

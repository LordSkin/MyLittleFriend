package kramnik.bartlomiej.mylittlefriend.Root;

import android.app.Application;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseServer;
import kramnik.bartlomiej.mylittlefriend.Presenter.AppPresenter;
import kramnik.bartlomiej.mylittlefriend.Presenter.Dagger.DaggerPresenterComponent;
import kramnik.bartlomiej.mylittlefriend.Presenter.Dagger.PresenterComponent;
import kramnik.bartlomiej.mylittlefriend.Presenter.Dagger.PresenterModule;
import kramnik.bartlomiej.mylittlefriend.Root.Dagger.AppComponent;
import kramnik.bartlomiej.mylittlefriend.Root.Dagger.AppModule;
import kramnik.bartlomiej.mylittlefriend.Root.Dagger.DaggerAppComponent;

/**
 * Created by Mao on 04.02.2018.
 */

public class App extends Application {

    private AppComponent appComponent;
    private AppPresenter presenter;
    private static String myIP;

    public static String getMyIP() {
        return myIP;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        getIP();
        final App app = this;
        Observable<PresenterComponent> observable = new Observable<PresenterComponent>() {
            @Override
            protected void subscribeActual(Observer<? super PresenterComponent> observer) {
                observer.onNext(DaggerPresenterComponent.builder().presenterModule(new PresenterModule(app)).build());
            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<PresenterComponent>() {
                    @Override
                    public void onSubscribe(Disposable d) {}
                    @Override
                    public void onNext(PresenterComponent value) {
                        value.inject(presenter);
                    }
                    @Override
                    public void onError(Throwable e) {}
                    @Override
                    public void onComplete() {}
                });

        presenter = new AppPresenter();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(presenter)).build();
        ResponseServer server = new ResponseServer();
        appComponent.inject(server);

    }

    private void getIP(){
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        boolean isIPv4 = sAddr.indexOf(':')<0;
                        if(isIPv4){
                            myIP = sAddr;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            myIP ="";
        }
    }

}

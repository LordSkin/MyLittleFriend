package kramnik.bartlomiej.mylittlefriend.Root;

import android.app.Application;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import kramnik.bartlomiej.mylittlefriend.Presenter.AppPresenter;
import kramnik.bartlomiej.mylittlefriend.Presenter.Dagger.DaggerPresenterComponent;
import kramnik.bartlomiej.mylittlefriend.Presenter.Dagger.PresenterComponent;
import kramnik.bartlomiej.mylittlefriend.Presenter.Dagger.PresenterModule;
import kramnik.bartlomiej.mylittlefriend.Root.Dagger.AppComponent;
import kramnik.bartlomiej.mylittlefriend.Root.Dagger.AppModule;

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

        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        myIP = addr.getHostAddress();
                    }
                }
            }
        }
        catch (Exception ex) {
            myIP ="";
        }

        PresenterComponent presenterComponent =  DaggerPresenterComponent.builder().presenterModule(new PresenterModule(this)).build();
        presenter = new AppPresenter();
        presenterComponent.inject(presenter);

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(presenter)).build();

    }


}

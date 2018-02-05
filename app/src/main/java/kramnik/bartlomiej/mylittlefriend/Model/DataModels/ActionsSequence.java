package kramnik.bartlomiej.mylittlefriend.Model.DataModels;

import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import kramnik.bartlomiej.mylittlefriend.Root.App;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by Mao on 30.01.2018.
 */

public class ActionsSequence {

    private List<Action> actions;
    private String ipAddress;

    public ActionsSequence() {
        this.ipAddress = App.getMyIP();
        actions = new ArrayList<Action>();
    }

    public ActionsSequence(List<Action> actions) {
        this.actions = actions;
        this.ipAddress = App.getMyIP();
    }

    public void addAction(Action action){
        actions.add(action);
    }

    public void deleteAction(int i){
        actions.remove(i);
    }

    public void clearActions(){
        actions.clear();
    }


}

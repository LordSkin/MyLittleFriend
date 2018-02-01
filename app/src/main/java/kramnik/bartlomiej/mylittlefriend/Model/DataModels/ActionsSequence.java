package kramnik.bartlomiej.mylittlefriend.Model.DataModels;

import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.WIFI_SERVICE;

/**
 * Created by Mao on 30.01.2018.
 */

public class ActionsSequence {

    private List<Action> actions;
    private String ipAddress;

    public ActionsSequence(String ip) {
        this.ipAddress = ip;
        actions = new ArrayList<Action>();
    }

    public ActionsSequence(String ipAddress, List<Action> actions) {
        this.actions = actions;
        this.ipAddress = ipAddress;
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

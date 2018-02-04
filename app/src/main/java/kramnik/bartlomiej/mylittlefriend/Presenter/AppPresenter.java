package kramnik.bartlomiej.mylittlefriend.Presenter;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Action;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;

/**
 * Created by Mao on 04.02.2018.
 */

public class AppPresenter implements SelectAgentPresenter, SendCommandPresenter {



    public AppPresenter() {
        RequestSender sender = new RequestSender();

    }

    //from sleectAgent activity
    @Override
    public void addAgent(Agent agent) {

    }

    @Override
    public void removeAgent(int pos) {

    }

    @Override
    public void SelectAgent(int pos) {

    }


    //from send command activity
    @Override
    public void addAction(Action action) {

    }

    @Override
    public void removeAction(int pos) {

    }

    @Override
    public void clearActions() {

    }

    @Override
    public void sendCommand() {

    }
}

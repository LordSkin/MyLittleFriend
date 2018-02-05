package kramnik.bartlomiej.mylittlefriend.Presenter;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Action;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseListener;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.SelectAgentView;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsView;

/**
 * Created by Mao on 04.02.2018.
 */

public class AppPresenter implements SelectAgentPresenter, SendCommandPresenter, ResponseListener {

    @Inject
    RequestSender sender;

    @Inject
    Context context;

    private SelectAgentView selectAgentView;
    private SendCommandsView sendCommandsView;



    public AppPresenter() {

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

    @Override
    public void setSelectAgentView(SelectAgentView view) {
        this.selectAgentView = view;
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

    @Override
    public void setSendCommandView(SendCommandsView view) {
        this.sendCommandsView = view;
    }

    //responses from http server
    @Override
    public void requestIncome(Object o) {
        Toast.makeText(context, o.toString(), Toast.LENGTH_SHORT).show();
    }
}

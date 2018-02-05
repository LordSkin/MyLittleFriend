package kramnik.bartlomiej.mylittlefriend.Presenter;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Action;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.ActionsSequence;
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

    private Agent selectedAgent;
    private ActionsSequence sequence;



    public AppPresenter() {
        selectedAgent = new Agent("ip", "name");
        sequence = new ActionsSequence();
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
        sequence.addAction(action);
    }

    @Override
    public void removeAction(int pos) {
        sequence.deleteAction(pos);
    }

    @Override
    public void clearActions() {
        sequence.clearActions();
    }

    @Override
    public void sendCommand() {
        try {
            sender.sendRequest(sequence, selectedAgent.getIp());
        }
        catch (Exception e) {
            e.printStackTrace();
            sendCommandsView.showMessage(e.toString());
        }
        finally {
            sequence.clearActions();
        }
    }

    @Override
    public void setSendCommandView(SendCommandsView view) {
        this.sendCommandsView = view;
    }

    //responses from http server
    @Override
    public void requestIncome(Object o) {
        selectAgentView.showMessage((String)o);
    }
}

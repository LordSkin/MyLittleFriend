package kramnik.bartlomiej.mylittlefriend.Presenter;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsDataBase;
import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsList;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Action;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.ActionsSequence;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseListener;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.AgentConnector;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;
import kramnik.bartlomiej.mylittlefriend.View.Dialogs.AddAgent;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.ListAdapter.AgentsAdapter;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.SelectAgentView;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsView;

/**
 * Created by Mao on 04.02.2018.
 */

public class AppPresenter implements SelectAgentPresenter, SendCommandPresenter, ResponseListener, AgentsAdapter, AddAgent {

    @Inject
    AgentConnector sender;

    @Inject
    Context context;

    @Inject
    AgentsList agentsList;

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
            sender.sendCommands(sequence, selectedAgent.getIp());

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

    //from Agentadapter - for listView
    @Override
    public int getCount() {
        return agentsList.getAgents().size();
    }

    @Override
    public Agent getItem(int pos) {
        return agentsList.getAgent(pos);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void addAgent(final String name, final String ipAddr) {

        Observable observable = new Observable() {
            @Override
            protected void subscribeActual(Observer observer) {
                agentsList.addAgent(new Agent(ipAddr, name));
                observer.onNext(new Object());
            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        selectAgentView.refreshList();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

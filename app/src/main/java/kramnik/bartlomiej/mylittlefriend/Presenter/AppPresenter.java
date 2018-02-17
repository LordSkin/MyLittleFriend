package kramnik.bartlomiej.mylittlefriend.Presenter;

import android.accounts.NetworkErrorException;
import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsList;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Action;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.ActionsSequence;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Observation;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseListener;
import kramnik.bartlomiej.mylittlefriend.Model.Notifications.NotificationsMenager;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.AgentConnector;
import kramnik.bartlomiej.mylittlefriend.Model.Services.ServiceDataProvider;
import kramnik.bartlomiej.mylittlefriend.View.Dialogs.AddAgent;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.ListAdapter.AgentsAdapter;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.SelectAgentView;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsView;

/**
 * Created by Mao on 04.02.2018.
 */

public class AppPresenter implements SelectAgentPresenter, SendCommandPresenter, ResponseListener, AgentsAdapter, AddAgent, ServiceDataProvider, ShowObservationsPresenter {

    @Inject
    AgentConnector sender;

    @Inject
    Context context;

    @Inject
    AgentsList agentsList;

    @Inject
    NotificationsMenager notifications;

    private SelectAgentView selectAgentView;
    private SendCommandsView sendCommandsView;

    private Agent selectedAgent;
    private ActionsSequence sequence;


    private PublishSubject<Agent> addToDbObservable;
    private PublishSubject<Integer> removeFromDbObservable;
    private PublishSubject<Void> sendRequestObservable;


    public AppPresenter() {
        selectedAgent = new Agent("ip", "name");
        sequence = new ActionsSequence();

        addToDbObservable = PublishSubject.create();
        addToDbObservable.observeOn(Schedulers.newThread()).subscribe(new Observer<Agent>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Agent value) {
                agentsList.addAgent(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        removeFromDbObservable = PublishSubject.create();
        removeFromDbObservable.observeOn(Schedulers.newThread()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                agentsList.deleteAgent(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        sendRequestObservable = PublishSubject.create();
        sendRequestObservable.observeOn(Schedulers.newThread()).subscribe(new Observer<Void>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Void value) {
                try {
                    sender.sendCommands(sequence, selectedAgent.getIp());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (NetworkErrorException e) {
                    e.printStackTrace();
                }
                finally {
                    sequence.clearActions();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    //from sleectAgent activity
    @Override
    public void addAgent(Agent agent) {
        addToDbObservable.onNext(agent);
    }

    @Override
    public void removeAgent(int pos) {
        removeFromDbObservable.onNext(pos);
    }

    @Override
    public void SelectAgent(int pos) {
        selectedAgent = agentsList.getAgent(pos);
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
        sendRequestObservable.onNext(null);
    }

    @Override
    public void setSendCommandView(SendCommandsView view) {
        this.sendCommandsView = view;
    }

    //responses from http server
    @Override
    public void requestIncome(Observation o, String agentIP) {
        Agent sender = null;
        for (Agent a : agentsList.getAgents()) {
            if (a.getIp().equals(agentIP)) sender = a;
        }
        if (sender != null) notifications.postNewObservation(context, o, sender);
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
    public void addAgent(String name, String ipAddr) {
        addAgent(new Agent(ipAddr, name));
        selectAgentView.refreshList();
    }


    @Override
    public AgentsList getAgentsList() {
        return agentsList;
    }


    @Override
    public SelectAgentView getSelectAgentView() {
        return selectAgentView;
    }


    //from ShowObservations
    @Override
    public Object getObservations() {
        return null;
    }
}

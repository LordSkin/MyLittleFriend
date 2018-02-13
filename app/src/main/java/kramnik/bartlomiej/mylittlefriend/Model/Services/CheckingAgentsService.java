package kramnik.bartlomiej.mylittlefriend.Model.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

import javax.inject.Inject;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.AgentConnector;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;
import kramnik.bartlomiej.mylittlefriend.Root.App;

/**
 * Service updating status of agents
 */

public class CheckingAgentsService extends IntentService {

    @Inject
    ServiceDataProvider presenter;

    private AgentConnector agentConnector;


    public CheckingAgentsService() {
        super("Checking agents service");
        agentConnector = new RequestSender();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ((App) this.getApplication()).getAppComponent().inject(this);

        while (true){
            if (presenter.getAgentsList()!=null&&presenter.getAgentsList().getAgents().size()>0){
                for (int i = 0; i < presenter.getAgentsList().getAgents().size(); i++) {
                    Agent agent = presenter.getAgentsList().getAgents().get(i);
                    try {
                        agent.setStatus(agentConnector.checkStatus(agent.getIp()));
                    }
                    catch (IOException e) {
                        agent.setStatus(Agent.UNREACHABLE);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
//                    if((new Random()).nextBoolean())agent.setStatus(Agent.WORKING);
//                    else agent.setStatus(Agent.READY);
                }
                presenter.getSelectAgentView().refreshList();
                try {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
    }


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }
}

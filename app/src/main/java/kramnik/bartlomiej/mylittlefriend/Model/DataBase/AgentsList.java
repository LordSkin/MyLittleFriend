package kramnik.bartlomiej.mylittlefriend.Model.DataBase;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;

/**
 * Created by Mao on 10.02.2018.
 */

public class AgentsList {

    private  AgentsDataBase dataBase;
    private List<Agent> agents;

    public AgentsList(Context context) {
        dataBase = Room.databaseBuilder(context, AgentsDataBase.class, "myLittleFriendDatabase").build();
        agents = dataBase.agentDao().getAll();
    }

    public List<Agent> getAgents(){
        return agents;
    }

    public Agent getAgent(int pos){
        return agents.get(pos);
    }

    public void deleteAgent(int pos){
        agents.remove(pos);
        dataBase.agentDao().deleteAgent(agents.get(pos));
    }

    public void addAgent(Agent agent){
        agents.add(agent);
        dataBase.agentDao().addAgent(agent);
    }




}

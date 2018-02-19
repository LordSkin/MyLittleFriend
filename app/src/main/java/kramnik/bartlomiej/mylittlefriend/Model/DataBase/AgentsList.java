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
        dataBase = Room.databaseBuilder(context, AgentsDataBase.class, "myLittleFriendDatabase2").build();
        agents = dataBase.agentDao().getAll();
    }

    public AgentsList(AgentsDataBase dataBase){
        this.dataBase = dataBase;
        agents = dataBase.agentDao().getAll();
    }

    public List<Agent> getAgents(){
        return agents;
    }

    public Agent getAgent(int pos){
        return agents.get(pos);
    }

    public void deleteAgent(int pos){
        Agent a = agents.get(pos);
        agents.remove(pos);
        dataBase.agentDao().deleteAgent(a);
    }

    public void deleteAgent(Agent agent){
        if(!agents.contains(agent)) throw new ArrayIndexOutOfBoundsException();
        agents.remove(agent);
        dataBase.agentDao().deleteAgent(agent);
    }

    public void addAgent(Agent agent){
        if(agent==null) throw new NullPointerException();
        agents.add(agent);
        dataBase.agentDao().addAgent(agent);
    }




}

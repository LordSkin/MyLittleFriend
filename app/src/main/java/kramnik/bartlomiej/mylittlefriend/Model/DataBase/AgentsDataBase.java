package kramnik.bartlomiej.mylittlefriend.Model.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;

/**
 * DataBase class required by room
 */

@Database(entities = {Agent.class}, version = 2)
public abstract class AgentsDataBase extends RoomDatabase {
    public abstract AgentDao agentDao();
}

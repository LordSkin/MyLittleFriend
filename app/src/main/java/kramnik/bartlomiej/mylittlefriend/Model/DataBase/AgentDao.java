package kramnik.bartlomiej.mylittlefriend.Model.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;

/**
 * Dao for agents entity required by room library
 */

@Dao
public interface AgentDao {

    @Query("SELECT * FROM Agent")
    List<Agent> getAll();

    @Insert
    void addAgent(Agent agent);

    @Delete
    void deleteAgent(Agent agent);
}

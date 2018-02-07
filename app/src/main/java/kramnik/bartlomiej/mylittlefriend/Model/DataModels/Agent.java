package kramnik.bartlomiej.mylittlefriend.Model.DataModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Datamodel for agent, used in database
 */

@Entity
public class Agent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "ip")
    private String ip;

    @ColumnInfo(name = "name")
    private String name;

    public Agent(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

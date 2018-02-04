package kramnik.bartlomiej.mylittlefriend.Model.DataModels;

/**
 * Created by Mao on 04.02.2018.
 */

public class Agent {
    private String ip;
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
}

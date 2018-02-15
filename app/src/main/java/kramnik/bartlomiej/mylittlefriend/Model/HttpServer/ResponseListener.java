package kramnik.bartlomiej.mylittlefriend.Model.HttpServer;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Observation;

/**
 * Created by Mao on 05.02.2018.
 */

public interface ResponseListener {
    void requestIncome(Observation o, String agentIP);
}

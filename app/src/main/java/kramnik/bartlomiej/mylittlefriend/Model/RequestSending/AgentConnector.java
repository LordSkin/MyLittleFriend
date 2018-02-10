package kramnik.bartlomiej.mylittlefriend.Model.RequestSending;

import android.accounts.NetworkErrorException;

import java.io.IOException;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.ActionsSequence;

/**
 * Created by Mao on 10.02.2018.
 */

public interface AgentConnector {


    void sendCommands(ActionsSequence sequence, String ip) throws IOException, NetworkErrorException;

    int checkStatus(String ip) throws IOException;
}

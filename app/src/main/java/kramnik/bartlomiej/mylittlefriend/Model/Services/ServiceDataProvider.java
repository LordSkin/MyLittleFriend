package kramnik.bartlomiej.mylittlefriend.Model.Services;

import kramnik.bartlomiej.mylittlefriend.Model.DataBase.AgentsList;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.AgentConnector;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.SelectAgentView;

/**
 * Created by Mao on 13.02.2018.
 */

public interface ServiceDataProvider {
    AgentsList getAgentsList();

    SelectAgentView getSelectAgentView();
}

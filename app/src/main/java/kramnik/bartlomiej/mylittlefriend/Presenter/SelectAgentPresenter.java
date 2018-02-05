package kramnik.bartlomiej.mylittlefriend.Presenter;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.SelectAgentView;

/**
 * Created by Mao on 04.02.2018.
 */

public interface SelectAgentPresenter {
    void addAgent(Agent agent);

    void removeAgent(int pos);

    void SelectAgent(int pos);

    void setSelectAgentView(SelectAgentView view);
}

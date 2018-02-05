package kramnik.bartlomiej.mylittlefriend.Presenter;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Action;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsView;

/**
 * Created by Mao on 04.02.2018.
 */

public interface SendCommandPresenter {
    void addAction(Action action);

    void removeAction(int pos);

    void clearActions();

    void sendCommand();

    void setSendCommandView(SendCommandsView view);

}

package kramnik.bartlomiej.mylittlefriend.Root.Dagger;

import dagger.Component;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.SelectAgentView;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsView;

/**
 * Created by Mao on 04.02.2018.
 */

@Component(modules = AppModule.class)
public interface AppComponent {

    public void inject(SelectAgentView view);

    public void inject(SendCommandsView view);
}

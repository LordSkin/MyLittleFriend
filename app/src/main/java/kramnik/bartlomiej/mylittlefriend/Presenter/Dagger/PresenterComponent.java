package kramnik.bartlomiej.mylittlefriend.Presenter.Dagger;

import dagger.Component;
import kramnik.bartlomiej.mylittlefriend.Presenter.AppPresenter;

/**
 * Created by Mao on 04.02.2018.
 */

@Component(modules = PresenterModule.class)
public interface PresenterComponent {
    public void inject(AppPresenter presenter);
}

package kramnik.bartlomiej.mylittlefriend.View.ViewObservations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import kramnik.bartlomiej.mylittlefriend.Presenter.ShowObservationsPresenter;
import kramnik.bartlomiej.mylittlefriend.R;
import kramnik.bartlomiej.mylittlefriend.Root.App;

public class ShowObservationsActivity extends AppCompatActivity implements ShowObservationsView {

    @Inject
    ShowObservationsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_observations);

        ((App)getApplication()).getAppComponent().inject(this);

        //((TextView)findViewById(R.id.text)).setText(presenter.getObservations().toString());
    }
}

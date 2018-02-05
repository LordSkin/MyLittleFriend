package kramnik.bartlomiej.mylittlefriend.View.SendCommands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Action;
import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseServer;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;
import kramnik.bartlomiej.mylittlefriend.Presenter.AppPresenter;
import kramnik.bartlomiej.mylittlefriend.Presenter.SendCommandPresenter;
import kramnik.bartlomiej.mylittlefriend.R;
import kramnik.bartlomiej.mylittlefriend.Root.App;

public class SendCommandsActivity extends AppCompatActivity implements View.OnClickListener, SendCommandsView {

    @Inject
    SendCommandPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_commands);

        ((Button)findViewById(R.id.lewo)).setOnClickListener(this);
        ((Button)findViewById(R.id.prawo)).setOnClickListener(this);
        ((Button)findViewById(R.id.jedz)).setOnClickListener(this);
        ((Button)findViewById(R.id.obserwuj)).setOnClickListener(this);
        ((Button)findViewById(R.id.OK)).setOnClickListener(this);


        ((App)getApplication()).getAppComponent().inject(this);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        presenter.setSendCommandView(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.lewo){
            presenter.addAction(new Action(Action.TURN,1));
        }
        if (view.getId()==R.id.prawo){
            presenter.addAction(new Action(Action.TURN,-1));
        }
        if (view.getId()==R.id.jedz){
            presenter.addAction(new Action(Action.MOVE,1));
        }
        if (view.getId()==R.id.obserwuj){
            presenter.addAction(new Action(Action.OBSERVE,1));
        }
        if (view.getId()==R.id.OK){
            presenter.sendCommand();
        }
    }

    @Override
    public void showMessage(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SendCommandsActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

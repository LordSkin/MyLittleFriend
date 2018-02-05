package kramnik.bartlomiej.mylittlefriend.View.SelectAgent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;

import kramnik.bartlomiej.mylittlefriend.Presenter.SelectAgentPresenter;
import kramnik.bartlomiej.mylittlefriend.R;
import kramnik.bartlomiej.mylittlefriend.Root.App;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsActivity;

public class SelectAgentActivity extends AppCompatActivity implements View.OnClickListener, SelectAgentView {

    @Inject
    SelectAgentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_agent);
        ((Button)findViewById(R.id.Button1)).setOnClickListener(this);

        ((App)getApplication()).getAppComponent().inject(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setSelectAgentView(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, SendCommandsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SelectAgentActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

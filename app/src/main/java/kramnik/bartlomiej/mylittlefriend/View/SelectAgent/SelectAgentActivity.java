package kramnik.bartlomiej.mylittlefriend.View.SelectAgent;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import javax.inject.Inject;

import kramnik.bartlomiej.mylittlefriend.Model.Services.CheckingAgentsService;
import kramnik.bartlomiej.mylittlefriend.Presenter.SelectAgentPresenter;
import kramnik.bartlomiej.mylittlefriend.R;
import kramnik.bartlomiej.mylittlefriend.Root.App;
import kramnik.bartlomiej.mylittlefriend.View.Dialogs.AddAgentDialog;
import kramnik.bartlomiej.mylittlefriend.View.SelectAgent.ListAdapter.AgentsListAdapter;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsActivity;

public class SelectAgentActivity extends AppCompatActivity implements View.OnClickListener, SelectAgentView, AdapterView.OnItemClickListener {

    @Inject
    SelectAgentPresenter presenter;

    private ListView listView;
    private AgentsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_agent);
        ((Button)findViewById(R.id.Button1)).setOnClickListener(this);
        ((FloatingActionButton)findViewById(R.id.addAgent)).setOnClickListener(this);

        ((App)getApplication()).getAppComponent().inject(this);

        listView = (ListView)findViewById(R.id.listView);

        adapter = new AgentsListAdapter();
        ((App)getApplication()).getAppComponent().inject(adapter);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        startService(new Intent(this, CheckingAgentsService.class));

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setSelectAgentView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, CheckingAgentsService.class));
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.Button1){
            Intent intent = new Intent(this, SendCommandsActivity.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.addAgent){
            AddAgentDialog dialog = new AddAgentDialog();
            ((App)getApplication()).getAppComponent().inject(dialog);
            dialog.show(getFragmentManager(), "Add agent");
        }
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

    @Override
    public void refreshList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        presenter.SelectAgent(i);
        Intent intent = new Intent(this, SendCommandsActivity.class);
        startActivity(intent);
    }
}

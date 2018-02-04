package kramnik.bartlomiej.mylittlefriend.View.SelectAgent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kramnik.bartlomiej.mylittlefriend.R;
import kramnik.bartlomiej.mylittlefriend.View.SendCommands.SendCommandsActivity;

public class SelectAgentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_agent);
        ((Button)findViewById(R.id.Button1)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, SendCommandsActivity.class);
        startActivity(intent);
    }
}

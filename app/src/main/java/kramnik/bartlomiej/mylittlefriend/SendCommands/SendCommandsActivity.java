package kramnik.bartlomiej.mylittlefriend.SendCommands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kramnik.bartlomiej.mylittlefriend.Model.HttpServer.ResponseServer;
import kramnik.bartlomiej.mylittlefriend.Model.RequestSending.RequestSender;
import kramnik.bartlomiej.mylittlefriend.R;

public class SendCommandsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_commands);

        final RequestSender sender = new RequestSender();

        ResponseServer s = new ResponseServer();

        //startService(intent);
    }
}

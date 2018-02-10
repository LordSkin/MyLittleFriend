package kramnik.bartlomiej.mylittlefriend.View.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.arch.persistence.room.PrimaryKey;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import kramnik.bartlomiej.mylittlefriend.R;

/**
 * Created by Mao on 10.02.2018.
 */

public class AddAgentDialog extends DialogFragment {

    @Inject
    AddAgent addAgent;

    private EditText name, ipAddr;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_agent, null);

        name = (EditText)v.findViewById(R.id.name);
        ipAddr = (EditText)v.findViewById(R.id.ipaddr);

        builder.setView(v);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addAgent.addAgent(name.getText().toString(), ipAddr.getText().toString());
            }
        });


        return builder.create();
    }
}

package kramnik.bartlomiej.mylittlefriend.View.SelectAgent.ListAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import javax.inject.Inject;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import kramnik.bartlomiej.mylittlefriend.R;

/**
 * Created by Mao on 10.02.2018.
 */

public class AgentsListAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    @Inject
    AgentsAdapter agentsAdapter;


    @Override
    public int getCount() {
        return agentsAdapter.getCount();
    }

    @Override
    public Object getItem(int i) {
        return agentsAdapter.getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater==null) inflater = LayoutInflater.from(agentsAdapter.getContext());
        View result = inflater.inflate(R.layout.list_cell, null);
        ((TextView)result.findViewById(R.id.name)).setText(agentsAdapter.getItem(i).getName());
        if(agentsAdapter.getItem(i).getStatus()== Agent.READY){
            result.findViewById(R.id.indicator).setBackgroundColor(Color.BLUE);
        }
        if(agentsAdapter.getItem(i).getStatus()== Agent.WORKING){
            result.findViewById(R.id.indicator).setBackgroundColor(Color.YELLOW);
        }
        if(agentsAdapter.getItem(i).getStatus()== Agent.UNREACHABLE){
            result.findViewById(R.id.indicator).setBackgroundColor(Color.RED);
        }
        return result;
    }
}

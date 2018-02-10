package kramnik.bartlomiej.mylittlefriend.View.SelectAgent.ListAdapter;

import android.content.Context;

import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;

/**
 * Created by Mao on 10.02.2018.
 */

public interface AgentsAdapter {
    int getCount();

    Agent getItem(int pos);

    Context getContext();

}

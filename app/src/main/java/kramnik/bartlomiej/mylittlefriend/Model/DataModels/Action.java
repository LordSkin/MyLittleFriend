package kramnik.bartlomiej.mylittlefriend.Model.DataModels;

import com.google.gson.Gson;

/**
 * Data model for single action
 */

public class Action {

    public static final int TURN = 0;
    public static final int MOVE = 1;
    public static final int OBSERVE = 2;

    private String type;
    private int value;

    public Action(int type, int value) {
        this.value = value;
        switch (type){
            case TURN:
                this.type = "turn";
                break;
            case MOVE:
                this.type = "move";
                break;
            case OBSERVE:
                this.type = "observe";
                break;
        }
    }

}

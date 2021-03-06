package kramnik.bartlomiej.mylittlefriend.Model.HttpServer;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import fi.iki.elonen.NanoHTTPD;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Observation;

/**
 * Http server handling incoming request and using ResponseListener
 */

public class ResponseServer extends NanoHTTPD {

    @Inject
    ResponseListener listener;

    private Gson gson;

    /**
     * set listener
     *
     * @param
     */
    public void setListener(ResponseListener listener) {
        this.listener = listener;
    }

    /**
     * create and start server
     */
    public ResponseServer() {
        super(8080);
        gson = new Gson();
        try {
            start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * stop server
     */
    public void stopServer() {
        stop();
    }

    @Override
    public Response serve(IHTTPSession session) {
        Log.d("HTTP Server", "request recived");

        try {
            Integer contentLength = Integer.parseInt(session.getHeaders().get("content-length"));
            byte[] buffer = new byte[contentLength];
            session.getInputStream().read(buffer, 0, contentLength);
            String x = new String(buffer);
            String ip = gson.toJsonTree(x).getAsJsonObject().get("ip").getAsString();
            // TODO: 15.02.2018 convert x to Observation 
            if (listener != null) listener.requestIncome(new Observation(), ip);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return NanoHTTPD.newFixedLengthResponse("OK");
    }
}

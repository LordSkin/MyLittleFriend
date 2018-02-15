package kramnik.bartlomiej.mylittlefriend.Model.HttpServer;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import fi.iki.elonen.NanoHTTPD;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Observation;

/**
 * Created by Mao on 30.01.2018.
 */

public class ResponseServer extends NanoHTTPD {

    @Inject
    ResponseListener listener;

    public ResponseServer() {
        super(8080);
        try {
            start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        Log.d("HTTP Server","request recived");

        try{
            Integer contentLength = Integer.parseInt(session.getHeaders().get("content-length"));
            byte[] buffer = new byte[contentLength];
            session.getInputStream().read(buffer, 0, contentLength);
            String x =new String(buffer);
            // TODO: 15.02.2018 convert x to Observation 
            listener.requestIncome(new Observation(), "192.168.0.1");


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return NanoHTTPD.newFixedLengthResponse("OK");
    }
}

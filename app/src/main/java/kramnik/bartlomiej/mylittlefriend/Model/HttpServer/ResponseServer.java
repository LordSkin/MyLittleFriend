package kramnik.bartlomiej.mylittlefriend.Model.HttpServer;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by Mao on 30.01.2018.
 */

public class ResponseServer extends NanoHTTPD {


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
        Log.d("HTTP Server","request reciwed");

        try{
            Integer contentLength = Integer.parseInt(session.getHeaders().get("content-length"));
            byte[] buffer = new byte[contentLength];
            session.getInputStream().read(buffer, 0, contentLength);
            String x =new String(buffer);



        }
        catch (Exception e){
            e.printStackTrace();
        }
        return NanoHTTPD.newFixedLengthResponse("dupa");
    }
}

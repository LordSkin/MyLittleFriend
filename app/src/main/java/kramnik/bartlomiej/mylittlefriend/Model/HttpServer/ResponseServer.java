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

    Activity a;

    public ResponseServer(Activity context) {
        super(8080);
        a = context;
        try {
            start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        Log.d("aaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaa");
        a.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(a, "TO gowno działa!", Toast.LENGTH_SHORT).show();
            }
        });
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(session.getInputStream()));
            String body="";
            String line ="";
            while ((line=bufferedReader.readLine())!=null){
                body +=line;
            }
            body.charAt(1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return super.serve(session);
    }
}

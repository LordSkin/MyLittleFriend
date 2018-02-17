package kramnik.bartlomiej.mylittlefriend.Model.RequestSending;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.ActionsSequence;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.Agent;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Sending http requests to robot with specified commands
 */

public class RequestSender implements AgentConnector {

    private final MediaType JSON;
    private Gson gson;
    public static int port = 8080;

    public RequestSender() {
        JSON = MediaType.parse("application/json; charset=utf-8");
        gson = new Gson();
    }

    

    private void sendRequest(ActionsSequence sequence, String url) throws IOException, NetworkErrorException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, gson.toJson(sequence));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try{
            if(client.newCall(request).execute().code()!=200){
                throw new NetworkErrorException("error while sending request: wrong response code");
            }
        }
        catch (SocketTimeoutException e){
            throw new NetworkErrorException("error while sending request: timeout");
        }

        //return response.body().string();
    }

    @Override
    public void sendCommands(ActionsSequence sequence, String ip) throws IOException, NetworkErrorException {
        if(sequence==null||ip==null) throw  new NullPointerException();
        sendRequest(sequence, "http:\\\\"+ip+":"+port);
    }

    @Override
    public int checkStatus(String ip) {
        if (ip==null) throw new NullPointerException();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url("http:\\\\"+ip+":"+port)
                .get()
                .build();
        String s = null;
        try {
            s = client.newCall(request).execute().body().string();
        }
        catch (IOException e) {
            e.printStackTrace();
            return Agent.UNREACHABLE;
        }

        if(s.equals("idle")){
            return  Agent.READY;
        }
        if(s.equals("working")){
            return Agent.WORKING;
        }
        else return Agent.UNREACHABLE;
    }
}


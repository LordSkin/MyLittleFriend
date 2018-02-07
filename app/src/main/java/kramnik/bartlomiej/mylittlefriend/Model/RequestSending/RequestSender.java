package kramnik.bartlomiej.mylittlefriend.Model.RequestSending;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import kramnik.bartlomiej.mylittlefriend.Model.DataModels.ActionsSequence;
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

public class RequestSender extends Observable<String> {

    private final MediaType JSON;
    private Gson gson;

    public RequestSender() {
        JSON = MediaType.parse("application/json; charset=utf-8");
        gson = new Gson();
    }

    @Override
    protected void subscribeActual(Observer<? super String> observer) {

    }



    public void sendRequest(ActionsSequence sequence, String url) throws IOException, NetworkErrorException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, gson.toJson(sequence));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        if(client.newCall(request).execute().code()!=200){
            throw new NetworkErrorException("error while sending request: wrong response code");
        }
        //return response.body().string();
    }
}


package kramnik.bartlomiej.mylittlefriend.Model.RequestSending;

import com.google.gson.Gson;

import java.io.IOException;

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

public class RequestSender {

    private final MediaType JSON;
    private Gson gson;

    public RequestSender() {
        JSON = MediaType.parse("application/json; charset=utf-8");
        gson = new Gson();
    }

    public String sendRequest(ActionsSequence sequence, String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, gson.toJson(sequence));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}


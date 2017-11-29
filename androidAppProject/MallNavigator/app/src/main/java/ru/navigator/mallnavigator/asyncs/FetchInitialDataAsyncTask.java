package ru.navigator.mallnavigator.asyncs;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.navigator.mallnavigator.network.NetworkCallback;

public class FetchInitialDataAsyncTask extends AsyncTask<URL, Void, String> {
    private static String TAG = FetchInitialDataAsyncTask.class.toString();

    private NetworkCallback<String> networkCallback;

    public FetchInitialDataAsyncTask(NetworkCallback<String> networkCallback) {
        this.networkCallback = networkCallback;
    }

    @Override
    protected String doInBackground(URL... params) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(params[0])
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {

            return response.body().string();
        } catch (IOException e) {
            Log.e(TAG, "Could not execute request");
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String s) {

    }
}

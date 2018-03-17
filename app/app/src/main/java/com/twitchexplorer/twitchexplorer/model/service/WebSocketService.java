package com.twitchexplorer.twitchexplorer.model.service;

import com.google.gson.Gson;
import com.twitchexplorer.twitchexplorer.model.constants.Constants;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketService extends WebSocketListener {

    private OkHttpClient client;
    WebSocket ws;
    Gson gson = new Gson();

    public WebSocketService(RestApiService restApiService) {
        String url = ("ws://" + Constants.URL_AND_PORT + "/socket");
        Request request = new Request.Builder().url(url).build();
        ws = restApiService.createWebSocket(request, this);
    }


    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        UpdateObject updateObject = gson.fromJson(text, UpdateObject.class);
        if (callback != null)
            callback.onUpdate(updateObject);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        t.printStackTrace();
    }

    public void setCallback(WebSocketCallback callback) {
        this.callback = callback;
    }

    WebSocketCallback callback;

    public interface WebSocketCallback {
        void onUpdate(UpdateObject updateObject);
    }


    public class UpdateObject {

        public List<Long> userIds;
        public List<Long> streamIds;
    }
}

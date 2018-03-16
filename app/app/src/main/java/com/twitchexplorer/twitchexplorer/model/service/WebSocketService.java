package com.twitchexplorer.twitchexplorer.model.service;

import com.google.gson.Gson;
import com.twitchexplorer.twitchexplorer.model.constants.Constants;

import java.util.List;

import javax.annotation.Nullable;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketService extends WebSocketListener {

    WebSocket ws;
    Gson gson = new Gson();

    public WebSocketService(RestApiService restApiService) {
        Request request = new Request.Builder().url("ws://+" + Constants.URL_AND_PORT + "/socket").build();
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
    }

    public void setCallback(WebSocketCallback callback) {
        this.callback = callback;
    }

    WebSocketCallback callback;

    public interface WebSocketCallback {
        void onUpdate(UpdateObject updateObject);
    }

    public class UpdateObject {

        List<Long> userIds;

        List<Long> streamIds;
    }
}

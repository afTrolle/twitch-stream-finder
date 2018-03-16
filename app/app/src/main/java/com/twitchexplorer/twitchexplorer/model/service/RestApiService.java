package com.twitchexplorer.twitchexplorer.model.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twitchexplorer.twitchexplorer.model.constants.Constants;
import com.twitchexplorer.twitchexplorer.model.pojo.BroadcasterType;
import com.twitchexplorer.twitchexplorer.model.pojo.GamesLive;
import com.twitchexplorer.twitchexplorer.model.pojo.Language;
import com.twitchexplorer.twitchexplorer.model.pojo.LiveStreamUserVoteView;
import com.twitchexplorer.twitchexplorer.model.pojo.StreamType;
import com.twitchexplorer.twitchexplorer.model.pojo.UserType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


public class RestApiService {

    private final static String baseUrl = "http://" + Constants.URL_AND_PORT;
    private final static String gamesUrl = baseUrl + "/api/game";
    private final static String languagesUrl = baseUrl + "/api/stream/languages";
    private final static String userUrl = baseUrl + "/api/user";
    private final static String streamURL = baseUrl + "/api/stream";

    RequestQueue queue;

    private final Gson gson;

    private final OkHttpClient client;
    android.os.Handler handler;

    public RestApiService(Context context) {
        client = new OkHttpClient();
        gson = new Gson();
        handler = new android.os.Handler(context.getMainLooper());
    }

    private void runOnUiThread(Runnable r) {
        handler.post(r);
    }

    public WebSocket createWebSocket(Request request, WebSocketListener webSocketService) {
        return client.newWebSocket(request, webSocketService);
    }


    public interface RestResponse<T> {
        void onResponse(T response);
    }

    public interface RestError {
        void onError(Exception e);
    }

    private void getHTTP(final RestResponse response, final RestError restError, final String url, final Type type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();
                    okhttp3.Response resp = client.newCall(request).execute();
                    final Object o = gson.fromJson(resp.body().charStream(), type);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("done", "convertiondone");
                            response.onResponse(o);
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("done", "error,");
                            restError.onError(e);
                        }
                    });
                }
            }
        }).start();
    }

    private String appendParams(Map<String, String> map, String baseUrl) {
        if (map == null || map.size() == 0)
            return baseUrl;

        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("?");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            builder.append(entry.getKey()).append("=").append(entry.getValue());
            if (iterator.hasNext()) {
                builder.append("&");
            }
        }
        return builder.toString();
    }

    private void cancleAllOfTag(Object tag) {
        if (queue != null)
            queue.cancelAll(tag);
    }

    private static final Type gameList = new TypeToken<ArrayList<GamesLive>>() {
    }.getType();


    public void getGamesBeingPlayed(RestResponse<List<GamesLive>> restResponse, RestError restError) {
        getHTTP(restResponse, restError, gamesUrl, gameList);
    }

    private static final Type languageList = new TypeToken<ArrayList<Language>>() {
    }.getType();

    public void getLanguages(RestResponse<List<Language>> restResponse, RestError restError) {
        getHTTP(restResponse, restError, languagesUrl, languageList);
    }

    private static final Type userTypeList = new TypeToken<ArrayList<UserType>>() {
    }.getType();

    public void getUserTypes(RestResponse<List<UserType>> restResponse, RestError restError) {
        String url = userUrl + "/userTypes";
        getHTTP(restResponse, restError, url, userTypeList);
    }

    private static final Type broadcasterTypeList = new TypeToken<ArrayList<BroadcasterType>>() {
    }.getType();

    public void getBroadCasterTypes(RestResponse<List<BroadcasterType>> restResponse, RestError restError) {
        String url = userUrl + "/broadCasterTypes";
        getHTTP(restResponse, restError, url, broadcasterTypeList);
    }


    private static final Type streamTypeList = new TypeToken<ArrayList<StreamType>>() {
    }.getType();

    public void getStreamTypes(RestResponse<List<StreamType>> restResponse, RestError restError) {
        String url = streamURL + "/streamTypes";
        getHTTP(restResponse, restError, url, streamTypeList);
    }



/*
    public List<LiveStreamUserVoteView> search(SearchParams searchObject) {
        WebResource path = streamRes.path("/languages");
        if (searchObject != null)
            path.queryParams(searchObject.map);
        String response = path.get(String.class);
        return gson.fromJson(response, liveStreamList);
    }
    */

    private final Type liveStreamList = new TypeToken<List<LiveStreamUserVoteView>>() {
    }.getType();

    public void searchStreams(RestResponse<List<LiveStreamUserVoteView>> response, RestError restError, SearchParams params) {
        String url = streamURL + "/search";
        getHTTP(response, restError, appendParams(params.map, url), liveStreamList);
    }


    public static class SearchParams {
        final Map<String, String> map = new HashMap<>();

        public void setStreamTitle(String streamTitle) {
            map.put("streamTitle", streamTitle);
        }

        public void setMinViewCount(int minViewCount) {
            map.put("minViewCount", Integer.toString(minViewCount));
        }

        public void setMaxViewCount(int maxViewCount) {
            map.put("maxViewCount", Integer.toString(maxViewCount));
        }

        public void setGameID(int gameID) {
            map.put("gameId", Integer.toString(gameID));
        }

        public void setLanguageId(int languageId) {
            map.put("languageId", Integer.toString(languageId));
        }

        public void setStreamTypeId(int streamTypeId) {
            map.put("streamTypeId", Integer.toString(streamTypeId));
        }

        public void setMinStreamTime(int minStreamTime) {
            map.put("minStreamTime", Integer.toString(minStreamTime));
        }

        public void setMaxStreamTime(int maxStreamTime) {
            map.put("maxStreamTime", Integer.toString(maxStreamTime));
        }

        public void setUserName(String userName) {
            map.put("userName", userName);
        }

        public void setUserDescription(String userDescription) {
            map.put("userDescription", userDescription);
        }

        public void setUserTypeId(int userTypeId) {
            map.put("userTypeId", Integer.toString(userTypeId));
        }

        public void setMinFollowerCount(int minFollowerCount) {
            map.put("minFollowerCount", Integer.toString(minFollowerCount));
        }

        public void setMaxFollowerCount(int minFollowerCount) {
            map.put("maxFollowerCount", Integer.toString(minFollowerCount));
        }

        public void setMinVoteRatio(double minVoteRatio) {
            map.put("minVoteRatio", Double.toString(minVoteRatio));
        }

        public void setMaxVoteRatio(double maxVoteRatio) {
            map.put("maxVoteRatio", Double.toString(maxVoteRatio));
        }

        public void setLimit(int i) {
            map.put("limit", Integer.toString(i));
        }

        public void setBroadCasterTypeId(Integer broadcasterTypeId) {
            map.put("broadCasterTypeId", Integer.toString(broadcasterTypeId));
        }
    }

}

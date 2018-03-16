package twitch.explorer.restApi.websocket;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.Session;
import twitch.explorer.restApi.websocket.response.UpdateObject;

import java.io.IOException;
import java.util.ArrayList;

public class WebSocketHandler {

    private final static WebSocketHandler webSocketHandler = new WebSocketHandler();
    private final Gson gson = new Gson();

    public static WebSocketHandler getInstance() {
        return webSocketHandler;
    }

    private final ArrayList<Session> sessions = new ArrayList<>();

    public synchronized boolean addSession(Session session) {
        session.getRemote().setBatchMode(BatchMode.OFF);
        return sessions.add(session);
    }

    public synchronized boolean removeSession(Session session) {
        return sessions.remove(session);
    }

    public synchronized void sendStingToEveryone(String str) {
        for (Session session : sessions) {
            try {
                session.getRemote().sendString(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sendUpdateToEveryone(UpdateObject object) {
        String json = gson.toJson(object);
        sendStingToEveryone(json);
    }
}

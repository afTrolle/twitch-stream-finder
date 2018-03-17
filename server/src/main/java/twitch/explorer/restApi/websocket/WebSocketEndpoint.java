package twitch.explorer.restApi.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class WebSocketEndpoint {

    @OnWebSocketMessage
    public void onText(Session session, String message) {
        // TODO add code here to
        /*
        if (session.isOpen()) {
            System.out.printf("Echoing back message [%s]%n", message);
            // echo the message back
            session.getRemote().sendString(message, null);
        }*/
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Websocket Connected: " +session.getRemote().getInetSocketAddress());
        WebSocketHandler.getInstance().addSession(session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int closeCode, String reason) {
        WebSocketHandler.getInstance().removeSession(session);
    }
}

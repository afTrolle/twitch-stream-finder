package twitch.explorer.restApi.websocket;


import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet
public class WebSocket extends WebSocketServlet {
    @Override
    public void configure(WebSocketServletFactory factory) {
      //  factory.getPolicy().setIdleTimeout(10000);
        factory.register(WebSocketEndpoint.class);
    }

}

package twitch.explorer.restApi;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.HandlerContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionIdManager;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.session.DefaultSessionIdManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.session.*;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.HashConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import twitch.explorer.settings.Config;

import javax.servlet.SessionTrackingMode;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import static org.eclipse.jetty.http.HttpHeader.COOKIE;

public class RestServer {

    public RestServer() {

        Config appConfig = Config.get();


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/*");

        Server jettyServer = new Server(appConfig.getServerPort());
        jettyServer.setHandler(context);
        ResourceConfig config = new PackagesResourceConfig("twitch.explorer.restApi.endpoints");


        SessionHandler handler = context.getSessionHandler();
        handler.setMaxInactiveInterval(-1);
        handler.setRefreshCookieAge(-1);
        DefaultSessionIdManager sessionIdManager = new DefaultSessionIdManager(jettyServer, new Random());
        sessionIdManager.setWorkerName("derp");
        handler.setSessionIdManager(sessionIdManager);
       // System.out.println(handler.getSessionCookie());
       // DefaultSessionCache defaultSessionCache = new DefaultSessionCache(handler);
       // handler.setSessionCache(defaultSessionCache);
        //  handler.setSessionCookie("test");
        //  handler.setUsingCookies(true);
        //   handler.getSessionCache();
/*
        ServletContainer container = new ServletContainer(config);
        ServletHolder holder = new ServletHolder(container);
        context.addServlet(holder, "/api/*");

        ServletHolder staticHolder = new ServletHolder(new DefaultServlet());
        staticHolder.setInitParameter("resourceBase", "src/main/resources/web");
        staticHolder.setInitParameter("pathInfoOnly", "true");
        context.addServlet(staticHolder, "/*");
*/
        jettyServer.setHandler(context);
        try {
            jettyServer.start();
        } catch (Exception ignored) {
            jettyServer.destroy();
        }
    }

}

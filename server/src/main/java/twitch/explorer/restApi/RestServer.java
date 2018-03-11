package twitch.explorer.restApi;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.session.*;
import twitch.explorer.restApi.filter.SessionFilter;
import twitch.explorer.settings.Config;

import javax.servlet.DispatcherType;
import javax.servlet.SessionTrackingMode;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;


public class RestServer {

    public RestServer() {
        Config appConfig = Config.get();

        Server server = new Server(appConfig.getServerPort());
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        setSessionHandling(context);
        setStaticContent(context);
        setRestApiHandlers(context);

        EnumSet<DispatcherType> SCOPE = EnumSet.of(DispatcherType.REQUEST);
        context.addFilter(SessionFilter.class, "/*", SCOPE);

        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStaticContent(ServletContextHandler context) {
        DefaultServlet servlet = new DefaultServlet();
        ServletHolder holder = new ServletHolder(servlet);
        holder.setInitParameter("pathInfoOnly", "true");
        holder.setInitParameter("resourceBase", "src/main/resources/web");
        context.addServlet(holder, "/*");
    }

    private void setRestApiHandlers(ServletContextHandler context) {
        ResourceConfig config = new PackagesResourceConfig("twitch.explorer.restApi.endpoint");
        ServletContainer container = new ServletContainer(config);
        ServletHolder holder = new ServletHolder(container);
        context.addServlet(holder, "/api/*");
    }

    private void setSessionHandling(ServletContextHandler context) {
        HashSessionManager manager = new HashSessionManager();
        manager.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
        File yourFile = new File("sessions");
        manager.setIdleSavePeriod(-1);
        manager.setMaxInactiveInterval(-1);
        manager.setSavePeriod(1);
        try {
            manager.setStoreDirectory(yourFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.getSessionHandler().setSessionManager(manager);
    }


}

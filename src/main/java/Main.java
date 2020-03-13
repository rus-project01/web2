import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.ApiServlet;
import servlet.LoginServlet;
import servlet.RegistrationServlet;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception{
        ApiServlet apiServlet = new ApiServlet();
        RegistrationServlet reg = new RegistrationServlet();
        LoginServlet log = new LoginServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(apiServlet), "/api/*");
        context.addServlet(new ServletHolder(log), "/login");
        context.addServlet(new ServletHolder(reg), "/register");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}

package com.epam.training.movie.theater.driver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author Stas
 * @date 3/27/13
 */
public class ServerMain {

    public static void main(String args[]) throws Exception {

        String webappDirLocation = "src/main/webapp/";

        Server server = new Server(8080);
        WebAppContext root = new WebAppContext();

        root.setContextPath("/EpamSpringTrainingAdvanced");
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);

        root.setParentLoaderPriority(true);

        server.setHandler(root);

        server.start();
        server.join();
    }
}
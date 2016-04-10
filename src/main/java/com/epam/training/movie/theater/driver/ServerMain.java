package com.epam.training.movie.theater.driver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author Stas
 * @date 3/27/13
 */
public class ServerMain {

    public static void main(String args[]) throws Exception {

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("shutdown, here in memory db should be cleaned, do not know how yet");
            }
        }));

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

    private static class NoLogging implements Logger {
        @Override
        public String getName() {
            return "no";
        }

        @Override
        public void warn(String s, Object... objects) {

        }

        @Override
        public void warn(Throwable throwable) {

        }

        @Override
        public void warn(String s, Throwable throwable) {

        }

        @Override
        public void info(String s, Object... objects) {

        }

        @Override
        public void info(Throwable throwable) {

        }

        @Override
        public void info(String s, Throwable throwable) {

        }

        @Override
        public boolean isDebugEnabled() {
            return false;
        }

        @Override
        public void setDebugEnabled(boolean b) {

        }

        @Override
        public void debug(String s, Object... objects) {

        }

        @Override
        public void debug(String s, long l) {

        }

        @Override
        public void debug(Throwable throwable) {

        }

        @Override
        public void debug(String s, Throwable throwable) {

        }

        @Override
        public Logger getLogger(String s) {
            return null;
        }

        @Override
        public void ignore(Throwable throwable) {

        }
    }
}
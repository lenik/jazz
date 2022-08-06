package net.bodz.bas.httpd;

import javax.servlet.ServletContext;

import org.eclipse.jetty.server.handler.ContextHandler.Context;

import net.bodz.bas.program.skel.BasicCLI;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.site.vhost.VirtualHostManager;
import net.bodz.uni.echo.server.EchoServer;

/**
 * Bas embedded http server
 *
 * <p lang="zh-cn">
 * BAS 嵌入式 HTTP 服务器
 */
public abstract class BasHttpd
        extends BasicCLI {

    /**
     * Specify the host name.
     *
     * <p lang="zh-cn">
     * 指定主机名。
     *
     * @option -h --hostname =NAME
     */
    String hostName;

    /**
     * Specify the TCP port to listen.
     *
     * <p lang="zh-cn">
     * 指定监听的TCP端口号。
     *
     * @option -p --port =PORT
     */
    int port;

    @Override
    protected void mainImpl(String... args)
            throws Exception {
        initSystemGlobals();

        initVirtualHosts(VirtualHostManager.getInstance());

        ServletContextConfig config = createConfig();
        configure(config);

        if (hostName != null)
            config.setHostName(hostName);
        if (port != 0)
            config.setPortNumber(port);

        EchoServer server = new EchoServer(config);
        server.start();

        Context servletContext = server.getServletContextHandler().getServletContext();
        onContextStart(servletContext);
    }

    protected void initSystemGlobals()
            throws Exception {
    }

    protected void initVirtualHosts(VirtualHostManager manager)
            throws Exception {
    }

    protected ServletContextConfig createConfig() {
        ServletContextConfig config = new ServletContextConfig();
        return config;
    }

    protected abstract void configure(ServletContextConfig config);

    protected void onContextStart(ServletContext servletContext) {
    }

}

package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

public class ServerNaming {

    public static String getServerSimpleName(HttpServletRequest request) {
        // TODO request.getHeader("X-Forward-...");
        String serverName = request.getServerName();
        return getServerSimpleName(serverName);
    }

    public static String getServerSimpleName(String headName) {
        int dot = headName.indexOf('.');
        if (dot != -1)
            headName = headName.substring(0, dot);
        headName = headName.toLowerCase();
        return headName;
    }

}

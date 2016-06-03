package net.bodz.bas.site.vhost;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractVirtualHostResolver
        implements IVirtualHostResolver {

    @Override
    public int getPriority() {
        return 0;
    }

    protected String getHostName(HttpServletRequest request, boolean includeDomain) {
        // TODO request.getHeader("X-Forward-...");
        String hostName = request.getServerName();

        if (!includeDomain) {
            int dot = hostName.indexOf('.');
            hostName = dot == -1 ? hostName : hostName.substring(0, dot);
        }

        hostName = hostName.toLowerCase();
        return hostName;
    }

}

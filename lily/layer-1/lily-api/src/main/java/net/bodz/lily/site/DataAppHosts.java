package net.bodz.lily.site;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.site.vhost.CurrentVirtualHost;
import net.bodz.bas.site.vhost.IVirtualHost;

public class DataAppHosts {

    public static IDataAppHost fromRequest() {
        IVirtualHost vhost = CurrentVirtualHost.getVirtualHost();
        if (!(vhost instanceof IDataAppHost))
            throw new IllegalUsageException("Incompatible vhost.");

        IDataAppHost lilyHost = (IDataAppHost) vhost;
        return lilyHost;
    }

}

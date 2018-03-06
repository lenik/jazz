package net.bodz.bas.site;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostManager;

public abstract class DefaultSiteDirs
        implements IBasicSiteAnchors {

    File clusterDataDir;
    protected final Set<String> schemas = new HashSet<>();

    public DefaultSiteDirs() {
        File baseDir = SysProps.userHome;
        // File baseDir=SysProps.userWorkDir;
        // TODO appdata/APP/sites/SITE/files/SCHEMA/...
        clusterDataDir = new File(baseDir, "sites");
        clusterDataDir.mkdirs();
    }

    public File getDataDir(String instanceName) {
        return new File(clusterDataDir, instanceName);
    }

    public IAnchor getDataAnchor(String instanceName) {
        return _webApp_.join("files/");
    }

    protected void checkSchema(String schema) {
        if (schema == null)
            throw new NullPointerException("schema");
        if (!schemas.contains(schema))
            throw new IllegalArgumentException("Invalid schema name: " + schema);
    }

    public File getUploadDir(String instanceName, String schema) {
        if (instanceName == null)
            throw new NullPointerException("instanceName");
        checkSchema(schema);

        File dataDir = getDataDir(instanceName);
        // File filesDir = new File(dataDir, "files/" + schema);
        File filesDir = new File(dataDir, schema);
        filesDir.mkdirs();
        return filesDir;
    }

    public IAnchor getUploadAnchor(String instanceName, String schema) {
        if (instanceName == null)
            throw new NullPointerException("instanceName");
        checkSchema(schema);

        IAnchor data = getDataAnchor(instanceName);
        return data.join(schema + "/");
    }

    public File getUploadDir(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolve(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        String schema = request.getParameter("schema");
        if (schema == null)
            throw new IllegalArgumentException("Missing schema parameter");

        File dir = getUploadDir(vhost.getName(), schema);
        return dir;
    }

    public IAnchor getUploadAnchor(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolve(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        String schema = request.getParameter("schema");
        if (schema == null)
            throw new IllegalArgumentException("Missing schema parameter");

        return getUploadAnchor(vhost.getName(), schema);
    }

}

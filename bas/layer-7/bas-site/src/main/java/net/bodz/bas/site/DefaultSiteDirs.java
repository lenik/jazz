package net.bodz.bas.site;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.config.VhostResourceMappings;
import net.bodz.bas.site.file.UploadHint;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostManager;

/**
 * @see VhostResourceMappings#siteFilesAlias
 */
public class DefaultSiteDirs
        implements IBasicSiteAnchors {

    File clusterDataDir;
    Set<String> schemas = new HashSet<String>();
    boolean strict = false;

    public DefaultSiteDirs() {
        File baseDir = SysProps.userHome;
        // File baseDir=SysProps.userWorkDir;
        // TODO appdata/APP/sites/SITE/files/SCHEMA/...
        clusterDataDir = new File(baseDir, "sites");
        clusterDataDir.mkdirs();

        for (Class<?> uploadable : IndexedTypes.list(UploadHint.class, true)) {
            UploadHint aHint = uploadable.getAnnotation(UploadHint.class);
            String[] schemas = aHint.schemas();
            if (schemas.length == 0) {
                String schema = uploadable.getSimpleName();
                addSchema(schema);
            } else {
                for (String schema : schemas)
                    addSchema(schema);
            }
        }
    }

    /**
     * Where upload and other data files goes to.
     *
     * @see #getUploadDir(String, String)
     */
    public File getDataDir(String instanceName) {
        return new File(clusterDataDir, instanceName);
    }

    /**
     * How will the href part looks alike.
     *
     * After upload completes, the data anchor is used to fetch back the content.
     *
     * @see #getUploadedAnchor(HttpServletRequest)
     * @see VhostResourceMappings#siteFilesAlias
     */
    public IAnchor getDataAnchor(String instanceName) {
        return _webApp_.join("files/");
    }

    protected void addSchema(String schema) {
        schemas.add(schema);
    }

    protected void checkSchema(String schema) {
        if (schema == null)
            throw new NullPointerException("schema");
        if (strict)
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

    /**
     * @see BasicSite#PATH_UPLOAD
     */
    public IAnchor getUploadedAnchor(String instanceName, String schema) {
        if (instanceName == null)
            throw new NullPointerException("instanceName");
        checkSchema(schema);

        IAnchor data = getDataAnchor(instanceName);
        return data.join(schema + "/");
    }

    /**
     * @see BasicSite#PATH_UPLOAD
     */
    public IAnchor getUploadedAnchor(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolve(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        String schema = request.getParameter("schema");
        if (schema == null)
            throw new IllegalArgumentException("Missing schema parameter");

        return getUploadedAnchor(vhost.getName(), schema);
    }

    static DefaultSiteDirs instance = new DefaultSiteDirs();

    public static DefaultSiteDirs getInstance() {
        return instance;
    }

}

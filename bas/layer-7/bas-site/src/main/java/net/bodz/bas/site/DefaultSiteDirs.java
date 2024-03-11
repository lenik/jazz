package net.bodz.bas.site;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.config.VhostResourceMappings;
import net.bodz.bas.site.file.AttachmentSettings;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostManager;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @see VhostResourceMappings#siteFilesAlias
 */
public class DefaultSiteDirs
        implements
            IBasicSiteAnchors {

    static final String K_FILE_CLASS = "class";

    File clusterDataDir;
    Map<String, AttachmentSettings> fileClassMap = new HashMap<>();
    boolean strict = false;

    public DefaultSiteDirs() {
        File baseDir = SysProps.userHome;
        // File baseDir=SysProps.userWorkDir;
        // TODO appdata/APP/sites/SITE/files/<fileClass>/...
        clusterDataDir = new File(baseDir, "sites");
        clusterDataDir.mkdirs();

        for (Class<?> uploadable : IndexedTypes.list(HaveAttachments.class, true)) {
            HaveAttachments aHaveAttachments = uploadable.getAnnotation(HaveAttachments.class);
            if (aHaveAttachments != null) {
                String fileClass = uploadable.getSimpleName();
                AttachmentSettings settings = AttachmentSettings.parse(aHaveAttachments, fileClass);
                addFileClass(fileClass, settings);
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

    public File getDataDir(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");
        return new File(clusterDataDir, vhost.getName());
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

    protected void addFileClass(String fileClass, AttachmentSettings settings) {
        if (fileClass == null)
            throw new NullPointerException("fileClass");
        if (settings == null)
            throw new NullPointerException("settings");
        AttachmentSettings old = fileClassMap.get(fileClass);
        if (old != null)
            throw new DuplicatedKeyException(fileClass, old);
        fileClassMap.put(fileClass, settings);
    }

    protected void checkFileClass(String fileClass) {
        if (fileClass == null)
            throw new NullPointerException(K_FILE_CLASS);
        if (strict)
            if (! fileClassMap.containsKey(fileClass))
                throw new IllegalArgumentException("Invalid " + K_FILE_CLASS + " name: " + fileClass);
    }

    public File getUploadDir(String instanceName, String fileClass) {
        if (instanceName == null)
            throw new NullPointerException("instanceName");
        checkFileClass(fileClass);

        File dataDir = getDataDir(instanceName);
        // File filesDir = new File(dataDir, "files/" + location);
        File filesDir = new File(dataDir, fileClass);
        filesDir.mkdirs();
        return filesDir;
    }

    public File getUploadDir(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        String location = request.getParameter(K_FILE_CLASS);
        if (location == null)
            throw new IllegalArgumentException("Missing " + K_FILE_CLASS + " parameter");

        File dir = getUploadDir(vhost.getName(), location);
        return dir;
    }

    public File getUploadDir(HttpServletRequest request, String location) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        File dir = getUploadDir(vhost.getName(), location);
        return dir;
    }

    /**
     * @see BasicSite#PATH_UPLOAD
     */
    public IAnchor getUploadedAnchor(String instanceName, String fileClass) {
        if (instanceName == null)
            throw new NullPointerException("instanceName");
        checkFileClass(fileClass);

        IAnchor data = getDataAnchor(instanceName);
        return data.join(fileClass + "/");
    }

    /**
     * @see BasicSite#PATH_UPLOAD
     */
    public IAnchor getUploadedAnchor(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        String fileClass = request.getParameter(K_FILE_CLASS);
        if (fileClass == null)
            throw new IllegalArgumentException("Missing " + K_FILE_CLASS + " parameter");

        return getUploadedAnchor(vhost.getName(), fileClass);
    }

    static DefaultSiteDirs instance = new DefaultSiteDirs();

    public static DefaultSiteDirs getInstance() {
        return instance;
    }

}

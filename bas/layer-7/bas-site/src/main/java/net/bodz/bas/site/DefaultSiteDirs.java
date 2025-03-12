package net.bodz.bas.site;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.nio.file.FileFn;
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

/**
 * @see VhostResourceMappings#siteFilesAlias
 */
public class DefaultSiteDirs
        implements
            IBasicSiteAnchors {

    static final String K_FILE_CLASS = "class";

    Path clusterDataDir;
    Map<String, AttachmentSettings> fileClassMap = new HashMap<>();
    boolean strict = false;

    protected DefaultSiteDirs() {
        Path baseDir = SysProps.userHome;
        // File baseDir=SysProps.userWorkDir;
        // TODO appdata/APP/sites/SITE/files/<fileClass>/...
        clusterDataDir = baseDir.resolve("sites");

        for (Class<?> uploadable : IndexedTypes.list(HaveAttachments.class, true)) {
            HaveAttachments aHaveAttachments = uploadable.getAnnotation(HaveAttachments.class);
            if (aHaveAttachments != null) {
                String fileClass = uploadable.getSimpleName();
                AttachmentSettings settings = AttachmentSettings.parse(aHaveAttachments, fileClass);
                addFileClass(fileClass, settings);
            }
        }
    }

    public DefaultSiteDirs setUp()
            throws IOException {
        Files.createDirectories(clusterDataDir);
        return this;
    }

    /**
     * Where upload and other data files goes to.
     *
     * @see #getUploadDir(String, String)
     */
    public Path getDataDir(String instanceName) {
        return clusterDataDir.resolve(instanceName);
    }

    public Path getDataDir(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");
        return clusterDataDir.resolve(vhost.getName());
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
            if (!fileClassMap.containsKey(fileClass))
                throw new IllegalArgumentException("Invalid " + K_FILE_CLASS + " name: " + fileClass);
    }

    public Path getUploadDir(String instanceName, String fileClass)
            throws IOException {
        if (instanceName == null)
            throw new NullPointerException("instanceName");
        checkFileClass(fileClass);

        Path dataDir = getDataDir(instanceName);
        Path filesDir = dataDir.resolve(fileClass);
        if (!FileFn.mkdirs(filesDir, true))
            throw new IOException("Failed to mkdir " + filesDir);
        return filesDir;
    }

    public Path getUploadDir(HttpServletRequest request)
            throws IOException {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        String location = request.getParameter(K_FILE_CLASS);
        if (location == null)
            throw new IllegalArgumentException("Missing " + K_FILE_CLASS + " parameter");

        Path dir = getUploadDir(vhost.getName(), location);
        return dir;
    }

    public Path getUploadDir(HttpServletRequest request, String location)
            throws IOException {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(request);
        if (vhost == null)
            throw new IllegalUsageException("No corresponding vhost.");

        Path dir = getUploadDir(vhost.getName(), location);
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

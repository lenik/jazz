package net.bodz.lily.app;

import java.nio.file.Path;
import java.util.Collection;

import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.ctx.UnionDefaultContextIdsResolver;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.lily.entity.attachment.AttachmentGroup;
import net.bodz.lily.site.DataAppHosts;
import net.bodz.lily.site.IDataAppHost;
import net.bodz.lily.storage.IVolumeProvider;

public class DataApps {

    static final Logger logger = LoggerFactory.getLogger(DataApps.class);

    public static DataApplication getPreferred() {
        DataContext dataContext = DataHub.getPreferredHub().getMain();

        Path rootDir = SysProps.dataDir;
        Path baseDir = null;

        Collection<String> contextIds = //
                UnionDefaultContextIdsResolver.getInstance().resolveContextIds();
        for (String contextId : contextIds) {
            Path dir = rootDir.resolve(contextId);
            if (FileFn.isDirectory(dir)) {
                baseDir = dir;
                break;
            }
        }
        if (baseDir == null) {
            logger.error("baseDir isn't defined. mkdir it prior to server start.");
            for (String contextId : contextIds) {
                Path dir = rootDir.resolve(contextId);
                if (FileFn.notExists(dir))
                    logger.info("    example: mkdir " + dir);
            }
            baseDir = rootDir.resolve("__volumes");
        }

        IVolumeProvider volumeProvider = new AttachmentGroup(baseDir);

        DataApplication app = new DataApplication(dataContext, volumeProvider);
        return app;
    }

    public static IDataApplication fromRequest() {
        HttpServletRequest request = CurrentHttpService.getRequest();
        return fromRequest(request);
    }

    public static IDataApplication fromRequest(HttpServletRequest request) {
        IDataAppHost host = DataAppHosts.fromRequest(request);
        return host.getDataApp();
    }

    // public static IDataApplication get(HttpServletRequest req) {
//        return getOrCreate(req, false);
//    }
//
//    public static IDataApplication lazyCreate(HttpServletRequest req) {
//        return getOrCreate(req, true);
//    }

//
//    static IDataApplication getOrCreate(HttpServletRequest req, boolean create) {
//        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(req);
//        return getOrCreate(vhost, create);
//    }
//
//    public static IDataApplication get(IVirtualHost vhost) {
//        return getOrCreate(vhost, false);
//    }
//
//    public static IDataApplication lazyCreate(IVirtualHost vhost) {
//        return getOrCreate(vhost, true);
//    }
//
//    static IDataApplication getOrCreate(IVirtualHost vhost, boolean create) {
//        IDataApplication app = vhost.getAttribute(IDataApplication.ATTRIBUTE_KEY);
//        if (app == null && create) {
//            synchronized (vhost) {
//                if (app == null) {
//                    app = createForVhost(vhost);
//                    vhost.setAttribute(IDataApplication.ATTRIBUTE_KEY, app);
//                }
//            }
//        }
//        return app;
//    }

//    static IDataApplication createForReq(HttpServletRequest req) {
//        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(req);
//        return createForVhost(vhost);
//    }
//
//    static IDataApplication createForVhost(IVirtualHost vhost) {
//        ConnectOptions connectOptions = vhost.getAttribute(ConnectOptions.ATTRIBUTE_KEY);
//        DataContext dataContext = new DataContext(connectOptions);
//        File baseDir = DefaultSiteDirs.getInstance().getDataDir(vhost.getName());
//        IVolumeProvider volumeProvider = new AttachmentGroup(baseDir);
//        IDataApplication app = new DataApplication(dataContext, volumeProvider);
//        return app;
//    }

}

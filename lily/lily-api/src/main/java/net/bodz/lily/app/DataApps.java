package net.bodz.lily.app;

import java.io.File;
import java.util.Collection;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.ctx.UnionDefaultContextIdsResolver;
import net.bodz.lily.entity.attachment.AttachmentGroup;
import net.bodz.lily.site.DataAppHosts;
import net.bodz.lily.site.IDataAppHost;
import net.bodz.lily.storage.IVolumeProvider;

public class DataApps {

    public static DataApplication getPreferred() {
        DataContext dataContext = DataHub.getPreferredHub().getMain();

        File rootDir = SysProps.userHome;
        File baseDir = null;

        Collection<String> contextIds = //
                UnionDefaultContextIdsResolver.getInstance().resolveContextIds();
        for (String contextId : contextIds) {
            File dir = new File(rootDir, contextId);
            if (dir.isDirectory()) {
                baseDir = dir;
                break;
            }
        }
        if (baseDir == null)
            baseDir = new File(rootDir, "__volumes");
        IVolumeProvider volumeProvider = new AttachmentGroup(baseDir);

        DataApplication app = new DataApplication(dataContext, volumeProvider);
        return app;
    }

    public static IDataApplication fromRequest() {
        IDataAppHost host = DataAppHosts.fromRequest();
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

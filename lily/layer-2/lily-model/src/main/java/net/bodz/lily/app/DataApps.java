package net.bodz.lily.app;

import java.io.File;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.ctx.UnionDefaultContextIdsResolver;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.DefaultSiteDirs;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostManager;
import net.bodz.lily.entity.attachment.AttachmentGroup;
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

    public static IDataApplication get(HttpServletRequest req) {
        return getOrCreate(req, false);
    }

    public static IDataApplication lazyCreate(HttpServletRequest req) {
        return getOrCreate(req, true);
    }

    public static IDataApplication fromRequest() {
        HttpServletRequest request = CurrentHttpService.getRequest();
        return lazyCreate(request);
    }

    static IDataApplication getOrCreate(HttpServletRequest req, boolean create) {
        IVirtualHost vhost = VirtualHostManager.getInstance().getVirtualHostFromRequest(req);
        return getOrCreate(vhost, create);
    }

    public static IDataApplication get(IVirtualHost vhost) {
        return getOrCreate(vhost, false);
    }

    public static IDataApplication lazyCreate(IVirtualHost vhost) {
        return getOrCreate(vhost, true);
    }

    static IDataApplication getOrCreate(IVirtualHost vhost, boolean create) {
        IDataApplication app = vhost.getAttribute(IDataApplication.ATTRIBUTE_KEY);
        if (app == null && create) {
            synchronized (vhost) {
                if (app == null) {
                    app = createForVhost(vhost);
                    vhost.setAttribute(IDataApplication.ATTRIBUTE_KEY, app);
                }
            }
        }
        return app;
    }

    static IDataApplication createForReq(HttpServletRequest req) {
        IVirtualHost vhost = VirtualHostManager.getInstance().getVirtualHostFromRequest(req);
        return createForVhost(vhost);
    }

    static IDataApplication createForVhost(IVirtualHost vhost) {
        ConnectOptions connectOptions = vhost.getAttribute(ConnectOptions.ATTRIBUTE_KEY);
        DataContext dataContext = new DataContext(connectOptions);
        File baseDir = DefaultSiteDirs.getInstance().getDataDir(vhost.getName());
        IVolumeProvider volumeProvider = new AttachmentGroup(baseDir);
        IDataApplication app = new DataApplication(dataContext, volumeProvider);
        return app;
    }

}

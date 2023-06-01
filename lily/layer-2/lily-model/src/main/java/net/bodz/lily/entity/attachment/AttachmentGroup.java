package net.bodz.lily.entity.attachment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.DefaultSiteDirs;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostManager;
import net.bodz.bas.t.tuple.Split;
import net.bodz.lily.storage.IVolume;
import net.bodz.lily.storage.IVolumeProvider;

public class AttachmentGroup
        implements
            IVolumeProvider {

    File baseDir;

    public AttachmentGroup(File baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public IVolume getVolume(String id) {
        if (id == null)
            return null;
        Split protocolOther = Split.shift(id, ':');
        switch (protocolOther.a) {
        case "entity":
            return forEntity(protocolOther.b);

        default:
            throw new NoSuchKeyException(id);
        }
    }

    Map<String, EntityVolume> entityCache = new HashMap<>();

    synchronized EntityVolume forEntity(String name) {
        EntityVolume volume = entityCache.get(name);
        if (volume == null) {
            IAnchor entityAnchor = IBasicSiteAnchors._webApp_.join(name);
            File entityBaseDir = new File(baseDir, name);
            String id = "entity:" + name;
            volume = new EntityVolume(id, entityAnchor, entityBaseDir);
            entityCache.put(name, volume);
        }
        return volume;
    }

    static Map<File, AttachmentGroup> baseDirCache = new HashMap<>();

    public static synchronized AttachmentGroup forRequest() {
        HttpServletRequest request = CurrentHttpService.getRequest();
        return forRequest(request);
    }

    public static synchronized AttachmentGroup forRequest(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().getVirtualHostFromRequest(request);
        if (vhost == null)
            throw new IllegalUsageException("unknown virtual host");
        File baseDir = DefaultSiteDirs.getInstance().getDataDir(vhost.getName());
        AttachmentGroup group = baseDirCache.get(baseDir);
        if (group == null) {
            group = new AttachmentGroup(baseDir);
            baseDirCache.put(baseDir, group);
        }
        return group;
    }

}

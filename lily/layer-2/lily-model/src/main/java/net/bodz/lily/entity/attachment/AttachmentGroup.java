package net.bodz.lily.entity.attachment;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.DefaultSiteDirs;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.site.vhost.VirtualHostManager;
import net.bodz.bas.t.tuple.Split;

public class AttachmentGroup {

    File baseDir;

    public AttachmentGroup(File baseDir) {
        this.baseDir = baseDir;
    }

    public IAttachmentVolume getVolume(String id) {
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

    Map<String, EntityAttachmentVolume> entityCache = new HashMap<>();

    synchronized EntityAttachmentVolume forEntity(String name) {
        EntityAttachmentVolume volume = entityCache.get(name);
        if (volume == null) {
            IAnchor entityAnchor = IBasicSiteAnchors._webApp_.join(name);
            File entityBaseDir = new File(baseDir, name);
            String id = "entity:" + name;
            volume = new EntityAttachmentVolume(id, entityAnchor, entityBaseDir);
            entityCache.put(name, volume);
        }
        return volume;
    }

    static Map<File, AttachmentGroup> baseDirCache = new HashMap<>();

    public static synchronized AttachmentGroup forRequest(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().get(request);
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

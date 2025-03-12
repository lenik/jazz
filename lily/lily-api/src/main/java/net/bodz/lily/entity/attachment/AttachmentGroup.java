package net.bodz.lily.entity.attachment;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.nio.file.FileFn;
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

    Path baseDir;

    public AttachmentGroup(Path baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public IVolume getVolume(String volumeId) {
        if (volumeId == null)
            return null;
        Split protocolOther = Split.shift(volumeId, ':');
        String protocol = protocolOther.a;
        String name = protocolOther.b;
        switch (protocol) {
            case "entity":
                return resolveEntityVolume(name);

            case "incoming":
                return resolveIncomingVolume(name);

            default:
                throw new NoSuchKeyException(volumeId);
        }
    }

    Map<String, EntityVolume> entityCache = new HashMap<>();
    Map<String, EntityVolume> incomingCache = new HashMap<>();

    synchronized EntityVolume resolveEntityVolume(String name) {
        EntityVolume volume = entityCache.get(name);
        if (volume == null) {
            IAnchor anchor = IBasicSiteAnchors._webApp_.join(name).enter();
            Path entityBaseDir = baseDir.resolve(name);
            String id = "entity:" + name;
            volume = new EntityVolume(id, anchor, entityBaseDir);
            entityCache.put(name, volume);
        }
        return volume;
    }

    synchronized EntityVolume resolveIncomingVolume(String name) {
        EntityVolume volume = incomingCache.get(name);
        if (volume == null) {
            IAnchor anchor = IBasicSiteAnchors._webApp_.join(name).enter().join("incoming/");
            Path incomingDir = baseDir.resolve(name + "/tmp");
            if (FileFn.notExists(incomingDir)) {
                FileFn.mkdirs(incomingDir);
            }
            String id = "incoming:" + name;
            volume = new EntityVolume(id, anchor, incomingDir);
            incomingCache.put(name, volume);
        }
        return volume;
    }

    static Map<Path, AttachmentGroup> baseDirCache = new HashMap<>();

    public static synchronized AttachmentGroup forRequest() {
        HttpServletRequest request = CurrentHttpService.getRequest();
        return forRequest(request);
    }

    public static synchronized AttachmentGroup forRequest(HttpServletRequest request) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolveVirtualHost(request);
        if (vhost == null)
            throw new IllegalUsageException("unknown virtual host");
        Path baseDir = DefaultSiteDirs.getInstance().getDataDir(vhost.getName());
        AttachmentGroup group = baseDirCache.get(baseDir);
        if (group == null) {
            group = new AttachmentGroup(baseDir);
            baseDirCache.put(baseDir, group);
        }
        return group;
    }

}

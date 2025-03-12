package net.bodz.lily.entity.attachment;

import java.nio.file.Path;

import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.servlet.ctx.PathUtils;
import net.bodz.bas.t.tuple.Split;
import net.bodz.lily.storage.LocalVolume;

public class EntityVolume
        extends LocalVolume {

    static final String DIR_ATTACHMENT = "attachment";

    public EntityVolume(String id, IAnchor baseAnchor, Path baseDir) {
        super(id, baseAnchor, baseDir);
    }

    public EntityVolume(String id, String baseHref, Path baseDir) {
        super(id, baseHref, baseDir);
    }

    /**
     * path format: Foo/_id_/attachment/_FileName_
     */
    @Override
    public boolean isValidWebPath(String webPath) {
        if (webPath == null)
            throw new NullPointerException("webPath");
        webPath = normalize(webPath);
        String baseWebPath = getVolumeAnchor().toWebPath();
        if (PathUtils.parentWith(webPath, baseWebPath)) {
            String path = PathUtils.pathAfter(webPath, baseWebPath);
            Split idPath = Split.shift(path, '/');
            if (idPath.b != null) {
                Split attachmentPath = Split.shift(path, '/');
                if (attachmentPath.a.equals(DIR_ATTACHMENT))
                    if (attachmentPath.b != null)
                        return true;
            }
        }
        return false;
    }

    @Override
    public String convertWebPathToVolumePath(String webPath) {
        if (webPath == null)
            throw new NullPointerException("webPath");
        webPath = normalize(webPath);
        String path = getVolumeAnchor().hrefTo(webPath);
        Split idPath = Split.shift(path, '/');
        if (idPath.b != null) {
            Split attachmentPath = Split.shift(path, '/');
            if (attachmentPath.a.equals(DIR_ATTACHMENT))
                if (attachmentPath.b != null)
                    return idPath.a + "/" + attachmentPath.b;
        }
        return null;
    }

    @Override
    public IAnchor convertVolumePathToAnchor(String path) {
        if (path == null)
            throw new NullPointerException("path");
        Split idFile = Split.shift(path, '/');
        String id = idFile.a;
        String file = idFile.b;
        String href = id + "/" + DIR_ATTACHMENT + "/" + file;
        IAnchor anchor = baseAnchor.join(href);
        return anchor;
    }

}

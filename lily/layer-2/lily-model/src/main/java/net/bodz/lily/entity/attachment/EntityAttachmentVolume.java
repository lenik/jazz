package net.bodz.lily.entity.attachment;

import java.io.File;

import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.servlet.ctx.PathUtils;
import net.bodz.bas.t.tuple.Split;

public class EntityAttachmentVolume
        extends LocalAttachmentVolume {

    static final String DIR_ATTACHMENT = "attachment";

    public EntityAttachmentVolume(String id, IAnchor baseAnchor, File baseDir) {
        super(id, baseAnchor, baseDir);
    }

    public EntityAttachmentVolume(String id, String baseHref, File baseDir) {
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
        Split idPath = Split.shift(path, '/');
        String href = idPath.a + "/" + DIR_ATTACHMENT + "/" + idPath.b;
        IAnchor anchor = baseAnchor.join(href);
        return anchor;
    }

}

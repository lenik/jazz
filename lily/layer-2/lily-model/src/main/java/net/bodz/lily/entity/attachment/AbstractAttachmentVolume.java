package net.bodz.lily.entity.attachment;

import java.io.File;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.FileResource;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.IBasicSiteAnchors;

public abstract class AbstractAttachmentVolume
        implements
            IAttachmentVolume {

    String volumeId;
    IAnchor baseAnchor;

    public AbstractAttachmentVolume(String volumeId, IAnchor baseAnchor) {
        if (volumeId == null)
            throw new NullPointerException("volumeId");
        if (baseAnchor == null)
            throw new NullPointerException("baseAnchor");
        this.volumeId = volumeId;
        this.baseAnchor = baseAnchor;
    }

    public AbstractAttachmentVolume(String volumeId, String baseWebPath) {
        if (volumeId == null)
            throw new NullPointerException("volumeId");
        if (baseWebPath == null)
            throw new NullPointerException("baseWebPath");

        this.volumeId = volumeId;

        while (baseWebPath.endsWith("//"))
            baseWebPath = baseWebPath.substring(0, baseWebPath.length() - 1);
        if (!baseWebPath.endsWith("/"))
            baseWebPath += "/";

        this.baseAnchor = IBasicSiteAnchors._webApp_.join(baseWebPath);
    }

    @Override
    public String getVolumeId() {
        return volumeId;
    }

    @Override
    public IAnchor getVolumeAnchor() {
        return baseAnchor;
    }

    protected static String normalize(String href) {
        while (href.startsWith("./"))
            href = href.substring(2);
        return href;
    }

    @Override
    public boolean isValidWebPath(String webPath) {
        if (webPath == null)
            throw new NullPointerException("webPath");

        String baseWebPath = getVolumeAnchor().toWebPath();

        webPath = normalize(webPath);
        if (!webPath.startsWith(baseWebPath))
            return false;

        int baseLen = baseWebPath.length();
        int len = webPath.length();
        if (baseLen == len)
            return true;

        char look = webPath.charAt(baseLen);
        return look == '/';
    }

    @Override
    public String convertWebPathToVolumePath(String webPath) {
        if (webPath == null)
            throw new NullPointerException("webPath");

        webPath = normalize(webPath);

        String path = getVolumeAnchor().hrefTo(webPath);
        return path;
    }

    @Override
    public IAnchor convertVolumePathToAnchor(String path) {
        IAnchor anchor = baseAnchor.join(path);
        return anchor;
    }

    @Override
    public IStreamInputSource getInputSource(String path) {
        File file = getLocalFile(path);
        if (file == null)
            return null;
        else
            return new FileResource(file);
    }

    @Override
    public String toString() {
        return baseAnchor.toString();
    }

}

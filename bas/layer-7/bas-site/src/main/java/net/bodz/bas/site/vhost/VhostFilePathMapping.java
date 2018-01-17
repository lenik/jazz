package net.bodz.bas.site.vhost;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.http.ctx.IAnchor;
import net.bodz.bas.site.IBasicSiteAnchors;
import net.bodz.bas.site.file.AbstractFilePathMapping;

public class VhostFilePathMapping
        extends AbstractFilePathMapping {

    File startDir;

    /**
     * @see net.bodz.bas.c.system.SysProps#getUserHome()
     */
    public VhostFilePathMapping(File startDir) {
        if (startDir == null)
            throw new NullPointerException("startDir");
        this.startDir = startDir;
    }

    @Override
    public File getLocalRoot(HttpServletRequest req) {
        IVirtualHost vhost = VirtualHostManager.getInstance().resolve(req);
        if (vhost == null)
            throw new IllegalArgumentException("No vhost for the request.");
        String name = vhost.getName();
        if (name == null)
            throw new NullPointerException("vhost name");
        File localRoot = new File(startDir, name.trim());
        return localRoot;
    }

    @Override
    public String getServletPath() {
        throw new NotImplementedException();
    }

    @Override
    public IAnchor getRootAnchor() {
        return IBasicSiteAnchors._webApp_.join("upload/");
    }

}

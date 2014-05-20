package net.bodz.bas.site.org;

import java.util.Date;

import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

public class SiteGraph
        extends SiteGraphNode
        implements ICacheControl {

    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.WANTED;
    }

    @Override
    public int getMaxAge() {
        return 86400;
    }

    @Override
    public Date getLastModified() {
        return new Date();
    }

    @Override
    public String getETag() {
        return null;
    }

    @Override
    public boolean isWeakValidation() {
        return false;
    }

}

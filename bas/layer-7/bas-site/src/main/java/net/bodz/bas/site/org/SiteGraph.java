package net.bodz.bas.site.org;

import java.time.OffsetDateTime;

import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

public class SiteGraph
        extends SiteGraphNode
        implements
            ICacheControl {

    private static final long serialVersionUID = 1L;

    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.DEFAULT;
    }

    @Override
    public int getMaxAge() {
        return 86400;
    }

    @Override
    public OffsetDateTime getLastModified() {
        return OffsetDateTime.now();
    }

    @Override
    public boolean isWeakValidation() {
        return false;
    }

}

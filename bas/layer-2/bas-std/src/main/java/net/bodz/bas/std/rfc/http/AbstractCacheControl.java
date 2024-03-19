package net.bodz.bas.std.rfc.http;

import java.time.ZonedDateTime;

public class AbstractCacheControl
        implements
            ICacheControl {

    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.OPTIONAL;
    }

    @Override
    public int getMaxAge() {
        return 0;
    }

    @Override
    public ZonedDateTime getLastModified() {
        return ZonedDateTime.now();
    }

    @Override
    public boolean isWeakValidation() {
        return false;
    }

}

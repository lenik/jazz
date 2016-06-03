package net.bodz.bas.std.rfc.http;

public class AbstractCacheControl
        implements ICacheControl {

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
    public long getLastModified() {
        return System.currentTimeMillis();
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

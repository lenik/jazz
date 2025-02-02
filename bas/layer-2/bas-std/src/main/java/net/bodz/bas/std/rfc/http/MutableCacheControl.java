package net.bodz.bas.std.rfc.http;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class MutableCacheControl
        implements ICacheControl,
                   Serializable {

    CacheControlMode cacheControlMode = CacheControlMode.AUTO;
    CacheRevalidationMode cacheRevalidationMode = CacheRevalidationMode.OPTIONAL;
    Integer maxAge;
    OffsetDateTime lastModified = OffsetDateTime.now();
    boolean weakValidation;

    @Override
    public CacheControlMode getCacheControlMode() {
        return cacheControlMode;
    }

    public void setCacheControlMode(CacheControlMode cacheControlMode) {
        this.cacheControlMode = cacheControlMode;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return cacheRevalidationMode;
    }

    public void setCacheRevalidationMode(CacheRevalidationMode cacheRevalidationMode) {
        this.cacheRevalidationMode = cacheRevalidationMode;
    }

    @Override
    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public OffsetDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(OffsetDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean isWeakValidation() {
        return weakValidation;
    }

    public void setWeakValidation(boolean weakValidation) {
        this.weakValidation = weakValidation;
    }

}

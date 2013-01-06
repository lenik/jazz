package net.bodz.bas.repr.content.cache;

import java.util.Date;

import net.bodz.bas.c.object.Nullables;

public class MutableCacheControl
        implements ICacheControl {

    CacheControlMode cacheControlMode = CacheControlMode.AUTO;
    CacheRevalidationMode cacheRevalidateMode = CacheRevalidationMode.OPTIONAL;
    Date createdDate;
    Date lastModifiedDate;
    int maxAge;

    @Override
    public CacheControlMode getCacheControlMode() {
        return cacheControlMode;
    }

    public void setCacheControlMode(CacheControlMode cacheControlMode) {
        if (cacheControlMode == null)
            throw new NullPointerException("cacheControlMode");
        this.cacheControlMode = cacheControlMode;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return cacheRevalidateMode;
    }

    public void setCacheRevalidateMode(CacheRevalidationMode cacheRevalidateMode) {
        if (cacheRevalidateMode == null)
            throw new NullPointerException("cacheRevalidateMode");
        this.cacheRevalidateMode = cacheRevalidateMode;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public void merge(ICacheControl o) {
        // PUBLIC -> PRIVATE -> NO_CACHE -> STORE.
        cacheControlMode = Nullables.max(cacheControlMode, o.getCacheControlMode());

        // OPTIONAL -> WANTED -> REQUIRED.
        cacheRevalidateMode = Nullables.min(cacheRevalidateMode, o.getCacheRevalidationMode());

        createdDate = Nullables.min(createdDate, o.getCreatedDate());
        lastModifiedDate = Nullables.max(lastModifiedDate, o.getLastModifiedDate());
        maxAge = Math.min(maxAge, o.getMaxAge());
    }

}

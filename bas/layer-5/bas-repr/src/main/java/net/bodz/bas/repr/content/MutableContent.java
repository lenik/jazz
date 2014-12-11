package net.bodz.bas.repr.content;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

public class MutableContent
        implements IContent {

    int priority;
    long creationDate = System.currentTimeMillis();

    CacheControlMode cacheControlMode = CacheControlMode.AUTO;
    CacheRevalidationMode cacheRevalidateMode = CacheRevalidationMode.OPTIONAL;
    int maxAge;
    long lastModified = creationDate;
    String eTag;
    boolean weakValidation;

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public long getCreationTime() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    /** ⇱ Implementation Of {@link ICacheControl}. */
    /* _____________________________ */static section.iface __CACHE__;

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
    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public long getLastModified() {
        return lastModified;
    }

    public void setLastModifiedDate(long lastModifiedDate) {
        this.lastModified = lastModifiedDate;
    }

    @Override
    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    @Override
    public boolean isWeakValidation() {
        return weakValidation;
    }

    public void setWeakValidation(boolean weakValidation) {
        this.weakValidation = weakValidation;
    }

    public void merge(ICacheControl o) {
        // PUBLIC -> PRIVATE -> NO_CACHE -> NO_STORE.
        cacheControlMode = Nullables.max(cacheControlMode, o.getCacheControlMode());

        // OPTIONAL -> WANTED -> REQUIRED.
        cacheRevalidateMode = Nullables.min(cacheRevalidateMode, o.getCacheRevalidationMode());

        lastModified = Nullables.max(lastModified, o.getLastModified());
        maxAge = Math.min(maxAge, o.getMaxAge());

        String eTag2 = o.getETag();
        if (eTag == null)
            eTag = eTag2;
        else if (eTag2 != null)
            eTag = eTag + "/" + eTag2;

        weakValidation |= o.isWeakValidation();
    }

}

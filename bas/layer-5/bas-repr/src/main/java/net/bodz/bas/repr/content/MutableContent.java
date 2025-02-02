package net.bodz.bas.repr.content;

import java.time.OffsetDateTime;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

public class MutableContent
        implements
            IContent {

    int priority;
    OffsetDateTime creationDate = OffsetDateTime.now();
    OffsetDateTime lastModified = creationDate;

    CacheControlMode cacheControlMode = CacheControlMode.AUTO;
    CacheRevalidationMode cacheRevalidateMode = CacheRevalidationMode.OPTIONAL;
    Integer maxAge;
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
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
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

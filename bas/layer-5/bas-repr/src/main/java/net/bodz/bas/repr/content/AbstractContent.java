package net.bodz.bas.repr.content;

import java.io.Serializable;

import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

public abstract class AbstractContent
        implements IContent, Serializable {

    private static final long serialVersionUID = 1L;

    private long creationDate = System.currentTimeMillis();
    private long lastModified = creationDate;

    @Override
    public int getPriority() {
        return 0;
    }

    /** ⇱ Implementation Of {@link IContent}. */
    /* _____________________________ */static section.iface __CONTENT__;

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
    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String getETag() {
        long time = getLastModified();
        return String.valueOf(time);
    }

    @Override
    public boolean isWeakValidation() {
        return false;
    }

}

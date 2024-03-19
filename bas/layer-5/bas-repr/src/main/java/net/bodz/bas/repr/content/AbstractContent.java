package net.bodz.bas.repr.content;

import java.io.Serializable;
import java.time.ZonedDateTime;

import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

public abstract class AbstractContent
        implements
            IContent,
            Serializable {

    private static final long serialVersionUID = 1L;

    private ZonedDateTime creationDate = ZonedDateTime.now();
    private ZonedDateTime lastModified = creationDate;

    @Override
    public int getPriority() {
        return 0;
    }

    /** ⇱ Implementation Of {@link IContent}. */
    /* _____________________________ */static section.iface __CONTENT__;

    @Override
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
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
        return CacheRevalidationMode.DEFAULT;
    }

    @Override
    public int getMaxAge() {
        return 86400;
    }

    @Override
    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean isWeakValidation() {
        return false;
    }

}

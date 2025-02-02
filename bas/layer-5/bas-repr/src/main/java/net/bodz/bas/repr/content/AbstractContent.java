package net.bodz.bas.repr.content;

import java.io.Serializable;
import java.time.OffsetDateTime;

import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

public abstract class AbstractContent
        implements
            IContent,
            Serializable {

    private static final long serialVersionUID = 1L;

    private OffsetDateTime creationDate = OffsetDateTime.now();
    private OffsetDateTime lastModified = creationDate;

    /** ⇱ Implementation Of {@link IContent}. */
    /* _____________________________ */static section.iface __CONTENT__;

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
        return CacheControlMode.AUTO;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.DEFAULT;
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
        return false;
    }

}

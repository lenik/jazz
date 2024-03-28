package net.bodz.bas.repr.content;

import java.time.OffsetDateTime;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.mda.xjdoc.model.javadoc.XjdocObject;

public abstract class AbstractXjdocContent
        extends XjdocObject
        implements
            IContent {

    private OffsetDateTime creationDate = OffsetDateTime.now();
    private OffsetDateTime lastModified = creationDate;

    @Override
    public int getPriority() {
        return 0;
    }

    /** ⇱ Implementation Of {@link ICacheControl}. */
/* _____________________________ */static section.iface __CACHE__;

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
        return 86400;
    }

    @Override
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
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
        OffsetDateTime time = getLastModified();
        return DateTimes.ISO8601.format(time);
    }

    @Override
    public boolean isWeakValidation() {
        return false;
    }

}

package net.bodz.bas.repr.content;

import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.mda.xjdoc.model.javadoc.XjdocObject;

public abstract class AbstractXjdocContent
        extends XjdocObject
        implements IContent {

    private long creationDate = System.currentTimeMillis();
    private long lastModified = creationDate;

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
        return CacheRevalidationMode.WANTED;
    }

    @Override
    public int getMaxAge() {
        return 86400;
    }

    @Override
    public long getCreationTime() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
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

package net.bodz.lily.type;

import java.util.Date;

import net.bodz.bas.i18n.dom1.SemiMutableElement;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

public class ManagedEntity
        extends SemiMutableElement
        implements IPosixAccessControl, ICacheControl {

    private static final long serialVersionUID = 1L;

    Date lastModified = new Date();

    int ownerId;
    int groupId;
    int accessMode = 0644;

    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.WANTED;
    }

    /**
     * One day by default.
     */
    @Override
    public int getMaxAge() {
        return 86400;
    }

    @Override
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        if (lastModified == null)
            throw new NullPointerException("lastModified");
        this.lastModified = lastModified;
    }

    @Override
    public String getETag() {
        long time = getLastModified().getTime();
        return String.valueOf(time);
    }

    @Override
    public boolean isWeakValidation() {
        return true;
    }

    @Override
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public int getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }

}

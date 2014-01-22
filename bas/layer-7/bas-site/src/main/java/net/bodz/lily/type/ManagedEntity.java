package net.bodz.lily.type;

import java.util.Date;

import net.bodz.bas.i18n.dom1.SemiMutableElement;
import net.bodz.bas.repr.content.cache.CacheControlMode;
import net.bodz.bas.repr.content.cache.CacheRevalidationMode;
import net.bodz.bas.repr.content.cache.ICacheControl;

public class ManagedEntity
        extends SemiMutableElement
        implements IPosixAccessControl, ICacheControl {

    private static final long serialVersionUID = 1L;

    Date createdDate = new Date();
    Date lastModifiedDate = createdDate;
    int maxAge; // in seconds.

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

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        if (createdDate == null)
            throw new NullPointerException("createdDate");
        this.createdDate = createdDate;
    }

    @Override
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        if (lastModifiedDate == null)
            throw new NullPointerException("lastModifiedDate");
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
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

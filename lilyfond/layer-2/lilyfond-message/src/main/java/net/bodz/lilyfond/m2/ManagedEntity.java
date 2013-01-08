package net.bodz.lilyfond.m2;

import java.util.Date;

import net.bodz.bas.i18n.dom1.SemiMutableElement;
import net.bodz.bas.repr.content.cache.CacheControlMode;
import net.bodz.bas.repr.content.cache.CacheRevalidationMode;
import net.bodz.bas.repr.content.cache.ICacheControl;

public class ManagedEntity
        extends SemiMutableElement
        implements ISimpleAccessControl, ICacheControl {

    private static final long serialVersionUID = 1L;

    Date createdDate = new Date();
    Date lastModifiedDate = createdDate;
    int maxAge; // in seconds.

    int ownerUserId;
    int ownerRoleId;
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
    public int getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(int ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    @Override
    public int getOwnerRoleId() {
        return ownerRoleId;
    }

    public void setOwnerRoleId(int ownerRoleId) {
        this.ownerRoleId = ownerRoleId;
    }

    @Override
    public int getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(int accessMode) {
        this.accessMode = accessMode;
    }

}

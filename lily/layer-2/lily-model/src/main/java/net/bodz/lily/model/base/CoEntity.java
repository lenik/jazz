package net.bodz.lily.model.base;

import java.io.Serializable;

import net.bodz.bas.repr.content.IContent;
import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;

import net.bodz.lily.model.base.security.IAccessControlled;

/**
 * Co/Con: Concrete, also Content, Controlled
 */
public abstract class CoEntity
        implements IContent, IStateful, IAccessControlled, Serializable {

    private static final long serialVersionUID = 1L;

    public static final int N_CODE_NAME = 20;
    public static final int N_LABEL = 80;
    public static final int N_DESCRIPTION = 200;

    public static final int S_INIT = 0;
    public static final int S_INVALID = -1;

    /** 私密 */
    public static final int M_PRIVATE = 0600;
    /** 共享 */
    public static final int M_SHARED = 0640;
    /** 协作 */
    public static final int M_COOP = 0660;
    /** 公开 */
    public static final int M_PUBLIC = 0644;
    /** 协作公开 */
    public static final int M_PUBLIC_COOP = 0664;

    private String codeName;
    private String label;
    private String description;
    private String image;

    private int priority;
    private long creationDate; // = System.currentTimeMillis();
    private long lastModified; // = creationDate;
    private int flags = 0;
    private int state = S_INIT;

    private int userId;
    private int groupId;
    private int accessMode = M_COOP;
    private int acl;

    // public abstract Serializable getIdentity();

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /** ⇱ Implementation Of {@link IContent}. */
    /* _____________________________ */static section.iface __CONTENT__;

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public void touch() {
        lastModified = System.currentTimeMillis();
        if (creationDate == 0L)
            creationDate = lastModified;
    }

    @Override
    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

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
    public String getETag() {
        long time = getLastModified();
        return String.valueOf(time);
    }

    @Override
    public boolean isWeakValidation() {
        return true;
    }

    /** ⇱ Implementation Of {@link IStateful}. */
    /* _____________________________ */static section.iface __STATE__;

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    /** ⇱ Implementation Of {@link IAccessControlled}. */
    /* _____________________________ */static section.iface __ACL__;

    @Override
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public int getAcl() {
        return acl;
    }

    public void setAcl(int acl) {
        this.acl = acl;
    }

    /** ⇱ Implementation Of {@link Object}. */
    /* _____________________________ */static section.iface __OBJ__;

    @Override
    public String toString() {
        return getCodeName() + " - " + getLabel();
    }

}

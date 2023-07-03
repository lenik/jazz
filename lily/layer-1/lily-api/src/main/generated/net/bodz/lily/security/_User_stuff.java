package net.bodz.lily.security;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;

/**
 * User Account
 */
@IdType(Integer.class)
public abstract class _User_stuff
        extends CoPrincipal {

    private static final long serialVersionUID = 1L;

    public static final int N_REFERER_ID = 10;

    private static final int _ord_TYPE_ID = 2;
    private static final int _ord_PRIMARY_GROUP_ID = 13;
    private static final int _ord_REFERER_ID = _ord_PRIMARY_GROUP_ID + 1;

    /** The primary user group, the default value of ownerGroup. */
    @NotNull
    Group primaryGroup;

    /** The primary user group, the default value of ownerGroup. */
    @NotNull
    int primaryGroupId;

    /** The referer user (used for promotion) */
    User referer;

    /** The referer user (used for promotion) */
    Integer refererId;

    /** User type like system-user, normal-user, etc. */
    @NotNull
    UserType type;

    /** User type like system-user, normal-user, etc. */
    @NotNull
    int typeId;

    /**
     * The primary user group, the default value of ownerGroup.
     *
     * @label gid0
     * @constraint foreign key (gid0) references lily.group (id)
     */
    @NotNull
    public Group getPrimaryGroup() {
        return primaryGroup;
    }

    /**
     * The primary user group, the default value of ownerGroup.
     */
    public void setPrimaryGroup(@NotNull Group value) {
        this.primaryGroup = value;
    }

    /**
     * The primary user group, the default value of ownerGroup.
     */
    @Ordinal(_ord_PRIMARY_GROUP_ID)
    @Precision(value = 10)
    @Column(name = "gid0", nullable = false, precision = 10)
    public synchronized int getPrimaryGroupId() {
        if (primaryGroup != null) {
            if (primaryGroup.getId() == null)
                return 0;
            return primaryGroup.getId();
        }
        return primaryGroupId;
    }

    /**
     * The primary user group, the default value of ownerGroup.
     */
    public synchronized void setPrimaryGroupId(int value) {
        this.primaryGroupId = value;
    }

    /**
     * The referer user (used for promotion)
     *
     * @label referer
     * @constraint foreign key (referer) references lily.user (id)
     */
    public User getReferer() {
        return referer;
    }

    /**
     * The referer user (used for promotion)
     */
    public void setReferer(User value) {
        this.referer = value;
    }

    /**
     * The referer user (used for promotion)
     */
    @Ordinal(_ord_REFERER_ID)
    @Precision(value = N_REFERER_ID)
    @Column(name = "referer", precision = 10)
    public synchronized Integer getRefererId() {
        if (referer != null) {
            return referer.getId();
        }
        return refererId;
    }

    /**
     * The referer user (used for promotion)
     */
    public synchronized void setRefererId(Integer value) {
        this.refererId = value;
    }

    /**
     * User type like system-user, normal-user, etc.
     *
     * @label type
     * @constraint foreign key (type) references lily.usertype (id)
     */
    @NotNull
    public UserType getType() {
        return type;
    }

    /**
     * User type like system-user, normal-user, etc.
     */
    public void setType(@NotNull UserType value) {
        this.type = value;
    }

    /**
     * User type like system-user, normal-user, etc.
     */
    @Ordinal(_ord_TYPE_ID)
    @Precision(value = 10)
    @Column(name = "type", nullable = false, precision = 10)
    public synchronized int getTypeId() {
        if (type != null) {
            return type.getId();
        }
        return typeId;
    }

    /**
     * User type like system-user, normal-user, etc.
     */
    public synchronized void setTypeId(int value) {
        this.typeId = value;
    }

    public void initNotNulls() {
    }

}

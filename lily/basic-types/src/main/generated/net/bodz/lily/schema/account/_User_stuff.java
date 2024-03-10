package net.bodz.lily.schema.account;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoPrincipal;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.contact.Person;

/**
 * User Account
 */
@IdType(Integer.class)
public abstract class _User_stuff
        extends CoPrincipal {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "user";

    public static final String FIELD_TYPE_ID = "type";
    public static final String FIELD_PRIMARY_GROUP_ID = "gid0";
    public static final String FIELD_REFERER_ID = "referer";
    public static final String FIELD_PERSON_ID = "person";

    public static final int N_TYPE_ID = 10;
    public static final int N_PRIMARY_GROUP_ID = 10;
    public static final int N_REFERER_ID = 10;
    public static final int N_PERSON_ID = 10;

    private static final int _ord_TYPE_ID = 2;
    private static final int _ord_PRIMARY_GROUP_ID = 14;
    private static final int _ord_REFERER_ID = _ord_PRIMARY_GROUP_ID + 1;
    private static final int _ord_PERSON_ID = _ord_REFERER_ID + 1;

    /**  */
    Person person;

    Integer personId;

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
     *
     * @constraint foreign key (person) references lily.person (id)
     */
    @JoinColumn(name = "person")
    @ManyToOne
    public Person getPerson() {
        return person;
    }

    /**
     */
    public void setPerson(Person value) {
        this.person = value;
    }

    @Ordinal(_ord_PERSON_ID)
    @Precision(value = N_PERSON_ID)
    @Column(name = "person", precision = 10)
    public synchronized Integer getPersonId() {
        if (person != null) {
            return person.getId();
        }
        return personId;
    }

    public synchronized void setPersonId(Integer value) {
        this.personId = value;
    }

    /**
     * The primary user group, the default value of ownerGroup.
     *
     * @constraint foreign key (gid0) references lily.group (id)
     */
    @JoinColumn(name = "gid0")
    @ManyToOne
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
     * @constraint foreign key (referer) references lily.user (id)
     */
    @JoinColumn(name = "referer")
    @ManyToOne
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
     * @constraint foreign key (type) references lily.usertype (id)
     */
    @JoinColumn(name = "type")
    @ManyToOne
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

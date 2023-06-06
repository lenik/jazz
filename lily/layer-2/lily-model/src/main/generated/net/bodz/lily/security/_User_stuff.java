package net.bodz.lily.security;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * User Account
 */
@IdType(Integer.class)
public abstract class _User_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_NAME = 32;
    public static final int N_REFERER_ID = 10;
    public static final int N_PERSON_ID = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_TYPE_ID = _ord_ID + 1;
    private static final int _ord_NAME = _ord_TYPE_ID + 1;
    private static final int _ord_GID0_ID = _ord_NAME + 10;
    private static final int _ord_REFERER_ID = _ord_GID0_ID + 1;
    private static final int _ord_PERSON_ID = _ord_REFERER_ID + 1;

    @Id
    @NotNull
    int id;

    /** The user name (unique) */
    @NotNull
    String name;

    /**  */
    Person person;

    Integer personId;

    /** The primary user group, the default value of ownerGroup. */
    @NotNull
    Group gid0;

    /** The primary user group, the default value of ownerGroup. */
    @NotNull
    int gid0Id;

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

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    /**
     * The user name (unique)
     */
    @Ordinal(_ord_NAME)
    @NotNull
    @Precision(value = N_NAME)
    @TextInput(maxLength = N_NAME)
    @Column(name = "name", nullable = false, length = N_NAME)
    public String getName() {
        return name;
    }

    /**
     * The user name (unique)
     */
    public void setName(@NotNull String value) {
        this.name = value;
    }

    /**
     *
     * @label person
     * @constraint foreign key (person) references lily.person (id)
     */
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
     * @label gid0
     * @constraint foreign key (gid0) references lily.group (id)
     */
    @NotNull
    public Group getGid0() {
        return gid0;
    }

    /**
     * The primary user group, the default value of ownerGroup.
     */
    public void setGid0(@NotNull Group value) {
        this.gid0 = value;
    }

    /**
     * The primary user group, the default value of ownerGroup.
     */
    @Ordinal(_ord_GID0_ID)
    @Precision(value = 10)
    @Column(name = "gid0", nullable = false, precision = 10)
    public synchronized int getGid0Id() {
        if (gid0 != null) {
            if (gid0.getId() == null)
                return 0;
            return gid0.getId();
        }
        return gid0Id;
    }

    /**
     * The primary user group, the default value of ownerGroup.
     */
    public synchronized void setGid0Id(int value) {
        this.gid0Id = value;
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
        this.name = "";
    }

}

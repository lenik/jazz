package net.bodz.violet.schema.asset;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.contact.OrgUnit;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;

@IdType(Long.class)
public abstract class _Asset_stuff
        extends AbstractAsset {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "asset";

    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_USER_ID = "o_user";
    public static final String FIELD_GROUP_ID = "o_group";
    public static final String FIELD_ORG_ID = "o_org";
    public static final String FIELD_ORG_UNIT_ID = "o_orgunit";
    public static final String FIELD_PERSON_ID = "o_person";

    public static final int N_BATCH = 2147483647;
    public static final int N_USER_ID = 10;
    public static final int N_GROUP_ID = 10;
    public static final int N_ORG_ID = 10;
    public static final int N_ORG_UNIT_ID = 10;
    public static final int N_PERSON_ID = 10;

    private static final int _ord_BATCH = 17;
    private static final int _ord_USER_ID = _ord_BATCH + 4;
    private static final int _ord_GROUP_ID = _ord_USER_ID + 1;
    private static final int _ord_ORG_ID = _ord_GROUP_ID + 1;
    private static final int _ord_ORG_UNIT_ID = _ord_ORG_ID + 1;
    private static final int _ord_PERSON_ID = _ord_ORG_UNIT_ID + 1;

    JsonVariant batch;

    /**  */
    Person person;

    Integer personId;

    /** (User Group) */
    Group group;

    Integer groupId;

    /**  */
    Organization org;

    Integer orgId;

    /**  */
    OrgUnit orgUnit;

    Integer orgUnitId;

    /** (User Account) */
    User user;

    Integer userId;

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public JsonVariant getBatch() {
        return batch;
    }

    public void setBatch(JsonVariant value) {
        this.batch = value;
    }

    /**
     *
     * @constraint foreign key (o_person) references lily.person (id)
     */
    @JoinColumn(name = "o_person")
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
    @Column(name = "o_person", precision = 10)
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
     * {inheritDoc Group}
     * User Group
     *
     * @constraint foreign key (o_group) references lily.group (id)
     */
    @JoinColumn(name = "o_group")
    @ManyToOne
    public Group getGroup() {
        return group;
    }

    /**
     * User Group
     */
    public void setGroup(Group value) {
        this.group = value;
    }

    @Ordinal(_ord_GROUP_ID)
    @Precision(value = N_GROUP_ID)
    @Column(name = "o_group", precision = 10)
    public synchronized Integer getGroupId() {
        if (group != null) {
            return group.getId();
        }
        return groupId;
    }

    public synchronized void setGroupId(Integer value) {
        this.groupId = value;
    }

    /**
     *
     * @constraint foreign key (o_org) references lily.org (id)
     */
    @JoinColumn(name = "o_org")
    @ManyToOne
    public Organization getOrg() {
        return org;
    }

    /**
     */
    public void setOrg(Organization value) {
        this.org = value;
    }

    @Ordinal(_ord_ORG_ID)
    @Precision(value = N_ORG_ID)
    @Column(name = "o_org", precision = 10)
    public synchronized Integer getOrgId() {
        if (org != null) {
            return org.getId();
        }
        return orgId;
    }

    public synchronized void setOrgId(Integer value) {
        this.orgId = value;
    }

    /**
     *
     * @constraint foreign key (o_orgunit) references lily.orgunit (id)
     */
    @JoinColumn(name = "o_orgunit")
    @ManyToOne
    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    /**
     */
    public void setOrgUnit(OrgUnit value) {
        this.orgUnit = value;
    }

    @Ordinal(_ord_ORG_UNIT_ID)
    @Precision(value = N_ORG_UNIT_ID)
    @Column(name = "o_orgunit", precision = 10)
    public synchronized Integer getOrgUnitId() {
        if (orgUnit != null) {
            return orgUnit.getId();
        }
        return orgUnitId;
    }

    public synchronized void setOrgUnitId(Integer value) {
        this.orgUnitId = value;
    }

    /**
     * {inheritDoc User}
     * User Account
     *
     * @constraint foreign key (o_user) references lily.user (id)
     */
    @JoinColumn(name = "o_user")
    @ManyToOne
    public User getUser() {
        return user;
    }

    /**
     * User Account
     */
    public void setUser(User value) {
        this.user = value;
    }

    @Ordinal(_ord_USER_ID)
    @Precision(value = N_USER_ID)
    @Column(name = "o_user", precision = 10)
    public synchronized Integer getUserId() {
        if (user != null) {
            return user.getId();
        }
        return userId;
    }

    public synchronized void setUserId(Integer value) {
        this.userId = value;
    }

    public void initNotNulls() {
    }

}

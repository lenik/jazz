package net.bodz.violet.asset;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.violet.art.Artifact;

@IdType(Long.class)
public abstract class _Asset_stuff
        extends AbstractAsset {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "asset";

    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_USER_ID = "o_user";
    public static final String FIELD_GROUP_ID = "o_group";
    public static final String FIELD_ORG_ID = "o_org";
    public static final String FIELD_ORG_UNIT_ID = "o_orgunit";
    public static final String FIELD_PERSON_ID = "o_person";

    public static final int N_ARTIFACT_ID = 10;
    public static final int N_BATCH = 2147483647;
    public static final int N_USER_ID = 10;
    public static final int N_GROUP_ID = 10;
    public static final int N_ORG_ID = 10;
    public static final int N_ORG_UNIT_ID = 10;
    public static final int N_PERSON_ID = 10;

    private static final int _ord_ARTIFACT_ID = 15;
    private static final int _ord_BATCH = _ord_ARTIFACT_ID + 2;
    private static final int _ord_USER_ID = _ord_BATCH + 4;
    private static final int _ord_GROUP_ID = _ord_USER_ID + 1;
    private static final int _ord_ORG_ID = _ord_GROUP_ID + 1;
    private static final int _ord_ORG_UNIT_ID = _ord_ORG_ID + 1;
    private static final int _ord_PERSON_ID = _ord_ORG_UNIT_ID + 1;

    Object batch;

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

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

    /** (User Account) */
    User user;

    Integer userId;

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object value) {
        this.batch = value;
    }

    /**
     *
     * @label o_person
     * @constraint foreign key (o_person) references lily.person (id)
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
     * @label o_group
     * @constraint foreign key (o_group) references lily.group (id)
     */
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
     * @label o_org
     * @constraint foreign key (o_org) references lily.org (id)
     */
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
     * @label o_orgunit
     * @constraint foreign key (o_orgunit) references lily.orgunit (id)
     */
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
     *
     * @label art
     * @constraint foreign key (art) references violet.art (id)
     */
    @NotNull
    public Artifact getArtifact() {
        return artifact;
    }

    /**
     */
    public void setArtifact(@NotNull Artifact value) {
        this.artifact = value;
    }

    @Ordinal(_ord_ARTIFACT_ID)
    @Precision(value = 10)
    @Column(name = "art", nullable = false, precision = 10)
    public synchronized int getArtifactId() {
        if (artifact != null) {
            return artifact.getId();
        }
        return artifactId;
    }

    public synchronized void setArtifactId(int value) {
        this.artifactId = value;
    }

    /**
     * {inheritDoc User}
     * User Account
     *
     * @label o_user
     * @constraint foreign key (o_user) references lily.user (id)
     */
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

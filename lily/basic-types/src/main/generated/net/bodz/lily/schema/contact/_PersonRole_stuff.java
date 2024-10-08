package net.bodz.lily.schema.contact;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _PersonRole_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "personrole";

    public static final String FIELD_ORG_ID = "org";
    public static final String FIELD_ORG_UNIT_ID = "ou";
    public static final String FIELD_PERSON_ID = "person";
    public static final String FIELD_ROLE = "role";

    public static final int N_ORG_ID = 10;
    public static final int N_ORG_UNIT_ID = 10;
    public static final int N_PERSON_ID = 10;
    public static final int N_ROLE = 20;

    private static final int _ord_ORG_ID = 2;
    private static final int _ord_ORG_UNIT_ID = _ord_ORG_ID + 1;
    private static final int _ord_PERSON_ID = _ord_ORG_UNIT_ID + 1;
    private static final int _ord_ROLE = _ord_PERSON_ID + 1;

    String role;

    /**  */
    OrgUnit orgUnit;

    Integer orgUnitId;

    /**  */
    @NotNull
    Person person;

    @NotNull
    int personId;

    /**  */
    @NotNull
    Organization org;

    @NotNull
    int orgId;

    @Ordinal(_ord_ROLE)
    @Precision(value = N_ROLE)
    @TextInput(maxLength = N_ROLE)
    @Column(name = "role", length = N_ROLE)
    public String getRole() {
        return role;
    }

    public void setRole(String value) {
        this.role = value;
    }

    /**
     *
     * @constraint foreign key (ou) references lily.orgunit (id)
     */
    @JoinColumn(name = "ou")
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
    @Column(name = "ou", precision = 10)
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
     * @constraint foreign key (person) references lily.person (id)
     */
    @JoinColumn(name = "person")
    @ManyToOne
    @NotNull
    public Person getPerson() {
        return person;
    }

    /**
     */
    public void setPerson(@NotNull Person value) {
        this.person = value;
    }

    @Ordinal(_ord_PERSON_ID)
    @Precision(value = 10)
    @Column(name = "person", nullable = false, precision = 10)
    public synchronized int getPersonId() {
        if (person != null) {
            if (person.getId() == null)
                return 0;
            return person.getId();
        }
        return personId;
    }

    public synchronized void setPersonId(int value) {
        this.personId = value;
    }

    /**
     *
     * @constraint foreign key (org) references lily.org (id)
     */
    @JoinColumn(name = "org")
    @ManyToOne
    @NotNull
    public Organization getOrg() {
        return org;
    }

    /**
     */
    public void setOrg(@NotNull Organization value) {
        this.org = value;
    }

    @Ordinal(_ord_ORG_ID)
    @Precision(value = 10)
    @Column(name = "org", nullable = false, precision = 10)
    public synchronized int getOrgId() {
        if (org != null) {
            if (org.getId() == null)
                return 0;
            return org.getId();
        }
        return orgId;
    }

    public synchronized void setOrgId(int value) {
        this.orgId = value;
    }

    public void initNotNulls() {
    }

}

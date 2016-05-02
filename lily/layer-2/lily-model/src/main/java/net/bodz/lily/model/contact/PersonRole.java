package net.bodz.lily.model.contact;

import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * 职位关联
 */
@IdType(Integer.class)
public class PersonRole
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_ROLE = 20;
    public static final int N_DESCRIPTION = 200;

    private Person person;
    private OrgUnit orgUnit;
    private Organization org;
    private String role;

    @NotNull
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPersonId(int personId) {
        (this.person = new Person()).setId(personId);
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit ou) {
        this.orgUnit = ou;
    }

    public void setOrgUnitId(int ouId) {
        (this.orgUnit = new OrgUnit()).setId(ouId);
    }

    @NotNull
    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public void setOrgId(int orgId) {
        (this.org = new Organization()).setId(orgId);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

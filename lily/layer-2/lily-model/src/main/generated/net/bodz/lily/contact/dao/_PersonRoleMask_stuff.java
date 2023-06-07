package net.bodz.lily.contact.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _PersonRoleMask_stuff
        extends CoObjectMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    Integer orgId;
    IntegerRange orgIdRange = new IntegerRange();

    Integer orgUnitId;
    IntegerRange orgUnitIdRange = new IntegerRange();

    Integer personId;
    IntegerRange personIdRange = new IntegerRange();

    String role;
    String rolePattern;

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public IntegerRange getIdRange() {
        return idRange;
    }

    public void setIdRange(IntegerRange range) {
        this.idRange = range;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer value) {
        this.orgId = value;
    }

    public IntegerRange getOrgIdRange() {
        return orgIdRange;
    }

    public void setOrgIdRange(IntegerRange range) {
        this.orgIdRange = range;
    }

    public Integer getOrgUnitId() {
        return orgUnitId;
    }

    public void setOrgUnitId(Integer value) {
        this.orgUnitId = value;
    }

    public IntegerRange getOrgUnitIdRange() {
        return orgUnitIdRange;
    }

    public void setOrgUnitIdRange(IntegerRange range) {
        this.orgUnitIdRange = range;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer value) {
        this.personId = value;
    }

    public IntegerRange getPersonIdRange() {
        return personIdRange;
    }

    public void setPersonIdRange(IntegerRange range) {
        this.personIdRange = range;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String value) {
        this.role = value;
    }

    public String getRolePattern() {
        return rolePattern;
    }

    public void setRolePattern(String value) {
        this.rolePattern = value;
    }

}

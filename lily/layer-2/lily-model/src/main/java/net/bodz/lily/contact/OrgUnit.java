package net.bodz.lily.contact;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

/**
 * 部门
 */
@Table(name = "orgunit")
@IdType(Integer.class)
public class OrgUnit
        extends CoNode<OrgUnit, Integer> {

    private static final long serialVersionUID = 1L;

    private Organization org;
    private final Contact contact = new Contact();
    private List<PersonRole> staff = new ArrayList<PersonRole>();

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public void setOrgId(int orgId) {
        (this.org = new Organization()).id(orgId);
    }

    public Contact getContact() {
        return contact;
    }

    public List<PersonRole> getStaff() {
        return staff;
    }

}
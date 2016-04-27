package net.bodz.lily.model.contact;

import java.util.ArrayList;
import java.util.List;

import net.bodz.lily.model.base.CoNode;
import net.bodz.lily.model.base.IdType;

/**
 * 部门
 */
@IdType(Integer.class)
public class OrgUnit
        extends CoNode<OrgUnit, Integer> {

    private static final long serialVersionUID = 1L;

    private Organization org;
    private Contact contact;
    private List<PersonRole> staff = new ArrayList<PersonRole>();

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public void setOrgId(int orgId) {
        (this.org = new Organization()).setId(orgId);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setContactId(int contactId) {
        (this.contact = new Contact()).setId(contactId);
    }

    public List<PersonRole> getStaff() {
        return staff;
    }

}
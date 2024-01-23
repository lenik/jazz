package net.bodz.lily.contact;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

/**
 * 部门
 */
@Table(schema = "lily", name = "orgunit")
public class OrgUnit
        extends _OrgUnit_stuff {

    private static final long serialVersionUID = 1L;

    private Contact contact = new Contact();
    private List<PersonRole> staff = new ArrayList<PersonRole>();

    @Override
    public Contact getContact() {
        return contact;
    }

    @Override
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<PersonRole> getStaff() {
        return staff;
    }
}

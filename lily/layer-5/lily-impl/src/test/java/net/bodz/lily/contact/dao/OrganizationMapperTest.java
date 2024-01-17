package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.ContactSamples;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.OrganizationSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class OrganizationMapperTest
        extends AbstractTableTest<Organization, OrganizationCriteriaBuilder, OrganizationMapper> {

    @Override
    public Organization buildSample()
            throws Exception {
        OrganizationSamples a = new OrganizationSamples();
        a.category = tables.pickAny(PartyCategoryMapper.class, "partycat");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.contact = new ContactSamples().build();
        return a.build();
    }

}

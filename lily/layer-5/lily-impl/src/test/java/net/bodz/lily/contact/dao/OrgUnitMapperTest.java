package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.ContactSamples;
import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.OrgUnitSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class OrgUnitMapperTest
        extends AbstractTableTest<OrgUnit, OrgUnitCriteriaBuilder, OrgUnitMapper> {

    @Override
    public OrgUnit buildSample()
            throws Exception {
        OrgUnitSamples a = new OrgUnitSamples();
        a.org = tables.pickAny(OrganizationMapper.class, "org");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(OrgUnitMapper.class, "orgunit");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.contact = new ContactSamples().build();
        return a.build();
    }

}

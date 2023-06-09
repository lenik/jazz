package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.PartyCategory;
import net.bodz.lily.contact.PartyCategorySamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PartyCategoryMapperTest
        extends AbstractTableTest<PartyCategory, PartyCategoryMask, PartyCategoryMapper> {

    @Override
    public PartyCategory buildSample()
            throws Exception {
        PartyCategorySamples a = new PartyCategorySamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(PartyCategoryMapper.class, "partycat");
        return a.build();
    }

}

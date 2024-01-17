package net.bodz.lily.inet.dao;

import net.bodz.lily.inet.ExternalSite;
import net.bodz.lily.inet.ExternalSiteSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ExternalSiteMapperTest
        extends AbstractTableTest<ExternalSite, ExternalSiteCriteriaBuilder, ExternalSiteMapper> {

    @Override
    public ExternalSite buildSample()
            throws Exception {
        ExternalSiteSamples a = new ExternalSiteSamples();
        a.parent = tables.pickAny(ExternalSiteMapper.class, "extsite");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}

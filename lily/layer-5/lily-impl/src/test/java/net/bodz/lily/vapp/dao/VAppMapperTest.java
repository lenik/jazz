package net.bodz.lily.vapp.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VApp;
import net.bodz.lily.vapp.VAppSamples;

public class VAppMapperTest
        extends AbstractTableTest<VApp, VAppCriteriaBuilder, VAppMapper> {

    @Override
    public VApp buildSample()
            throws Exception {
        VAppSamples a = new VAppSamples();
        a.category = tables.pickAny(VAppCatMapper.class, "vappcat");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.req = tables.pickAny(VAppRequestMapper.class, "vappreq");
        return a.build();
    }

}

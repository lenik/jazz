package net.bodz.lily.vapp.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VAppCat;
import net.bodz.lily.vapp.VAppCatSamples;

public class VAppCatMapperTest
        extends AbstractTableTest<VAppCat, VAppCatCriteriaBuilder, VAppCatMapper> {

    @Override
    public VAppCat buildSample()
            throws Exception {
        VAppCatSamples a = new VAppCatSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(VAppCatMapper.class, "vappcat");
        return a.build();
    }

}

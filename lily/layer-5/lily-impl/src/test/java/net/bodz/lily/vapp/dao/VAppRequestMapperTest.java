package net.bodz.lily.vapp.dao;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VAppRequest;
import net.bodz.lily.vapp.VAppRequestSamples;

public class VAppRequestMapperTest
        extends AbstractTableTest<VAppRequest, VAppRequestCriteriaBuilder, VAppRequestMapper> {

    @Override
    public VAppRequest buildSample()
            throws Exception {
        VAppRequestSamples a = new VAppRequestSamples();
        a.op = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}

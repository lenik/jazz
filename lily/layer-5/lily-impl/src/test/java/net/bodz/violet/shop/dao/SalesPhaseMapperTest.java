package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.SalesPhase;
import net.bodz.violet.shop.SalesPhaseSamples;

public class SalesPhaseMapperTest
        extends AbstractTableTest<SalesPhase, SalesPhaseMask, SalesPhaseMapper> {

    @Override
    public SalesPhase buildSample()
            throws Exception {
        SalesPhaseSamples a = new SalesPhaseSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}

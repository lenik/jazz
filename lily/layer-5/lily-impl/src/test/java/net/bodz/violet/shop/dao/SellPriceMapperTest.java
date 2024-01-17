package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.shop.SellPrice;
import net.bodz.violet.shop.SellPriceSamples;

public class SellPriceMapperTest
        extends AbstractTableTest<SellPrice, SellPriceCriteriaBuilder, SellPriceMapper> {

    @Override
    public SellPrice buildSample()
            throws Exception {
        SellPriceSamples a = new SellPriceSamples();
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}

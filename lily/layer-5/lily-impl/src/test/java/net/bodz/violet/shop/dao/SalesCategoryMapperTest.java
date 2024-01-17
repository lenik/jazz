package net.bodz.violet.shop.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.SalesCategory;
import net.bodz.violet.shop.SalesCategorySamples;

public class SalesCategoryMapperTest
        extends AbstractTableTest<SalesCategory, SalesCategoryCriteriaBuilder, SalesCategoryMapper> {

    @Override
    public SalesCategory buildSample()
            throws Exception {
        SalesCategorySamples a = new SalesCategorySamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(SalesCategoryMapper.class, "salecat");
        return a.build();
    }

}

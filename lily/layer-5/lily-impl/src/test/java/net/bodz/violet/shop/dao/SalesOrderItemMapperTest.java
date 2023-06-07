package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.shop.SalesOrderItem;
import net.bodz.violet.shop.SalesOrderItemSamples;

public class SalesOrderItemMapperTest
        extends AbstractTableTest<SalesOrderItem, SalesOrderItemMask, SalesOrderItemMapper> {

    @Override
    public SalesOrderItem buildSample()
            throws Exception {
        SalesOrderItemSamples a = new SalesOrderItemSamples();
        a.odr = tables.pickAny(SalesOrderMapper.class, "saleodr");
        a.shopItem = tables.pickAny(ShopItemMapper.class, "shopitem");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        return a.build();
    }

}

package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderItem;
import net.bodz.violet.shop.SalesOrderItemSamples;
import net.bodz.violet.shop.ShopItem;

public class SalesOrderItemMapperTest
        extends AbstractMapperTest<SalesOrderItem, SalesOrderItemMask, SalesOrderItemMapper> {

    @Override
    public SalesOrderItem buildSample() {
        SalesOrder order = tables.pickAny(SalesOrderMapper.class, "saleodr");
        ShopItem shopItem = tables.pickAny(ShopItemMapper.class, "shopitem");
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        return SalesOrderItemSamples.build(order, shopItem, artifact);
    }

}

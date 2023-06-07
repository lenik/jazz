package net.bodz.violet.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.shop.ShopItem;
import net.bodz.violet.shop.ShopItemSamples;

public class ShopItemMapperTest
        extends AbstractTableTest<ShopItem, ShopItemMask, ShopItemMapper> {

    @Override
    public ShopItem buildSample()
            throws Exception {
        ShopItemSamples a = new ShopItemSamples();
        a.category = tables.pickAny(ShopItemCategoryMapper.class, "shopitemcat");
        a.shop = tables.pickAny(ShopMapper.class, "shop");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        return a.build();
    }

}

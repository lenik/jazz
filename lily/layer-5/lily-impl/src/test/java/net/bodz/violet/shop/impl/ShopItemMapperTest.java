package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.shop.Shop;
import net.bodz.violet.shop.ShopItem;
import net.bodz.violet.shop.ShopItemCategory;
import net.bodz.violet.shop.ShopItemSamples;

public class ShopItemMapperTest
        extends AbstractTableTest<ShopItem, ShopItemMask, ShopItemMapper> {

    @Override
    public ShopItem buildSample() {
        Shop shop = tables.pickAny(ShopMapper.class, "shop");
        ShopItemCategory category = tables.pickAny(ShopItemCategoryMapper.class, "shopitemcat");
        Artifact art = tables.pickAny(ArtifactMapper.class, "art");
        return ShopItemSamples.build(shop, category, art);
    }

}

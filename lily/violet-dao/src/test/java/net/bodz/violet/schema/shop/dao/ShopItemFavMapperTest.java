package net.bodz.violet.schema.shop.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.shop.ShopItemFav;
import net.bodz.violet.schema.shop.ShopItemFavSamples;

public class ShopItemFavMapperTest
        extends AbstractTableTest<ShopItemFav, ShopItemFavMapper> {

    @Override
    public ShopItemFav buildSample()
            throws Exception {
        ShopItemFavSamples a = new ShopItemFavSamples();
        return a.buildWired(tables);
    }

}

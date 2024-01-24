package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.ShopItemFav;

public class ShopItemFavManager
        extends AbstractEntityManager<ShopItemFav, ShopItemFavMapper> {

    public ShopItemFavManager(DataContext dataContext) {
        super(dataContext, ShopItemFavMapper.class);
    }

}

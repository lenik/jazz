package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.ShopItemFav;

public class ShopItemFavManager
        extends AbstractEntityManager<ShopItemFav, ShopItemFavMapper> {

    public ShopItemFavManager(DataContext dataContext) {
        super(dataContext, ShopItemFavMapper.class);
    }

}

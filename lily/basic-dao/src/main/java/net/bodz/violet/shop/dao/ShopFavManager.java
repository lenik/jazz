package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.ShopFav;

public class ShopFavManager
        extends AbstractEntityManager<ShopFav, ShopFavMapper> {

    public ShopFavManager(DataContext dataContext) {
        super(dataContext, ShopFavMapper.class);
    }

}

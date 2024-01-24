package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.ShopFav;

public class ShopFavManager
        extends AbstractEntityManager<ShopFav, ShopFavMapper> {

    public ShopFavManager(DataContext dataContext) {
        super(dataContext, ShopFavMapper.class);
    }

}

package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.ShopTag;

public class ShopTagManager
        extends AbstractEntityManager<ShopTag, ShopTagMapper> {

    public ShopTagManager(DataContext dataContext) {
        super(dataContext, ShopTagMapper.class);
    }

}

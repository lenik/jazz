package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.SellPrice;

public class SellPriceManager
        extends AbstractEntityManager<SellPrice, SellPriceMapper> {

    public SellPriceManager(DataContext dataContext) {
        super(dataContext, SellPriceMapper.class);
    }

}

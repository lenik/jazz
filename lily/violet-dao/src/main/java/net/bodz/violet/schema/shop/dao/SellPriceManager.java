package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.SellPrice;

public class SellPriceManager
        extends AbstractEntityManager<SellPrice, SellPriceMapper> {

    public SellPriceManager(DataContext dataContext) {
        super(dataContext, SellPriceMapper.class);
    }

}

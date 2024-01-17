package net.bodz.violet.asset.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.asset.Asset;

public class AssetManager
        extends AbstractEntityManager<Asset, AssetMapper> {

    public AssetManager(DataContext dataContext) {
        super(dataContext, AssetMapper.class);
    }

}

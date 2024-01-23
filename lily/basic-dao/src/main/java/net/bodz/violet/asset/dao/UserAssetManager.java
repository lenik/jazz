package net.bodz.violet.asset.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.asset.UserAsset;

public class UserAssetManager
        extends AbstractEntityManager<UserAsset, UserAssetMapper> {

    public UserAssetManager(DataContext dataContext) {
        super(dataContext, UserAssetMapper.class);
    }

}

package net.bodz.violet.asset.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.asset.GroupAsset;

public class GroupAssetManager
        extends AbstractEntityManager<GroupAsset, GroupAssetMapper> {

    public GroupAssetManager(DataContext dataContext) {
        super(dataContext, GroupAssetMapper.class);
    }

}

package net.bodz.violet.schema.asset.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.asset.GroupAsset;

public class GroupAssetManager
        extends AbstractEntityManager<GroupAsset, GroupAssetMapper> {

    public GroupAssetManager(DataContext dataContext) {
        super(dataContext, GroupAssetMapper.class);
    }

}

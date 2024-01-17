package net.bodz.violet.asset.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.asset.OrgAsset;

public class OrgAssetManager
        extends AbstractEntityManager<OrgAsset, OrgAssetMapper> {

    public OrgAssetManager(DataContext dataContext) {
        super(dataContext, OrgAssetMapper.class);
    }

}

package net.bodz.violet.asset.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.asset.OrgUnitAsset;

public class OrgUnitAssetManager
        extends AbstractEntityManager<OrgUnitAsset, OrgUnitAssetMapper> {

    public OrgUnitAssetManager(DataContext dataContext) {
        super(dataContext, OrgUnitAssetMapper.class);
    }

}

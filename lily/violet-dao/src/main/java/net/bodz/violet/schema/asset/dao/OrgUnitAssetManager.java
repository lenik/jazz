package net.bodz.violet.schema.asset.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.asset.OrgUnitAsset;

public class OrgUnitAssetManager
        extends AbstractEntityManager<OrgUnitAsset, OrgUnitAssetMapper> {

    public OrgUnitAssetManager(DataContext dataContext) {
        super(dataContext, OrgUnitAssetMapper.class);
    }

}

package net.bodz.violet.schema.asset.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.asset.PersonAsset;

public class PersonAssetManager
        extends AbstractEntityManager<PersonAsset, PersonAssetMapper> {

    public PersonAssetManager(DataContext dataContext) {
        super(dataContext, PersonAssetMapper.class);
    }

}

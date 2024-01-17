package net.bodz.violet.asset.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.asset.PersonAsset;

public class PersonAssetManager
        extends AbstractEntityManager<PersonAsset, PersonAssetMapper> {

    public PersonAssetManager(DataContext dataContext) {
        super(dataContext, PersonAssetMapper.class);
    }

}

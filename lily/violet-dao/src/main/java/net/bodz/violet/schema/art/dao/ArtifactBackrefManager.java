package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactBackref;

public class ArtifactBackrefManager
        extends AbstractEntityManager<ArtifactBackref, ArtifactBackrefMapper> {

    public ArtifactBackrefManager(DataContext dataContext) {
        super(dataContext, ArtifactBackrefMapper.class);
    }

}

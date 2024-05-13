package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactParam;

public class ArtifactParamManager
        extends AbstractEntityManager<ArtifactParam, ArtifactParamMapper> {

    public ArtifactParamManager(DataContext dataContext) {
        super(dataContext, ArtifactParamMapper.class);
    }

}

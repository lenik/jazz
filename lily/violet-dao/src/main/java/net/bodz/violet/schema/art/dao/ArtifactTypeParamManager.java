package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactTypeParam;

public class ArtifactTypeParamManager
        extends AbstractEntityManager<ArtifactTypeParam, ArtifactTypeParamMapper> {

    public ArtifactTypeParamManager(DataContext dataContext) {
        super(dataContext, ArtifactTypeParamMapper.class);
    }

}

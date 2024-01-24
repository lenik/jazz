package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactParameter;

public class ArtifactParameterManager
        extends AbstractEntityManager<ArtifactParameter, ArtifactParameterMapper> {

    public ArtifactParameterManager(DataContext dataContext) {
        super(dataContext, ArtifactParameterMapper.class);
    }

}

package net.bodz.violet.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.art.ArtifactModel;

public class ArtifactModelManager
        extends AbstractEntityManager<ArtifactModel, ArtifactModelMapper> {

    public ArtifactModelManager(DataContext dataContext) {
        super(dataContext, ArtifactModelMapper.class);
    }

}

package net.bodz.violet.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.art.Artifact;

public class ArtifactManager
        extends AbstractEntityManager<Artifact, ArtifactMapper> {

    public ArtifactManager(DataContext dataContext) {
        super(dataContext, ArtifactMapper.class);
    }

}

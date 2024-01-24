package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactPhase;

public class ArtifactPhaseManager
        extends AbstractEntityManager<ArtifactPhase, ArtifactPhaseMapper> {

    public ArtifactPhaseManager(DataContext dataContext) {
        super(dataContext, ArtifactPhaseMapper.class);
    }

}

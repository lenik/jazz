package net.bodz.violet.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.art.ArtifactPhase;

public class ArtifactPhaseManager
        extends AbstractEntityManager<ArtifactPhase, ArtifactPhaseMapper> {

    public ArtifactPhaseManager(DataContext dataContext) {
        super(dataContext, ArtifactPhaseMapper.class);
    }

}

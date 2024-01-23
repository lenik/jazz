package net.bodz.violet.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.art.ArtifactTag;

public class ArtifactTagManager
        extends AbstractEntityManager<ArtifactTag, ArtifactTagMapper> {

    public ArtifactTagManager(DataContext dataContext) {
        super(dataContext, ArtifactTagMapper.class);
    }

}

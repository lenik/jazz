package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactTag;

public class ArtifactTagManager
        extends AbstractEntityManager<ArtifactTag, ArtifactTagMapper> {

    public ArtifactTagManager(DataContext dataContext) {
        super(dataContext, ArtifactTagMapper.class);
    }

}

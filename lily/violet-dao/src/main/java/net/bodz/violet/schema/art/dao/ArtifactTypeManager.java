package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactType;

public class ArtifactTypeManager
        extends AbstractEntityManager<ArtifactType, ArtifactTypeMapper> {

    public ArtifactTypeManager(DataContext dataContext) {
        super(dataContext, ArtifactTypeMapper.class);
    }

}

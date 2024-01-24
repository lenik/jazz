package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactDoc;

public class ArtifactDocManager
        extends AbstractEntityManager<ArtifactDoc, ArtifactDocMapper> {

    public ArtifactDocManager(DataContext dataContext) {
        super(dataContext, ArtifactDocMapper.class);
    }

}

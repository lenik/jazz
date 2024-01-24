package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.ArtifactCategory;

public class ArtifactCategoryManager
        extends AbstractEntityManager<ArtifactCategory, ArtifactCategoryMapper> {

    public ArtifactCategoryManager(DataContext dataContext) {
        super(dataContext, ArtifactCategoryMapper.class);
    }

}

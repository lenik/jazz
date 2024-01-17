package net.bodz.lily.schema.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.TagDef;

public class TagDefManager
        extends AbstractEntityManager<TagDef, TagDefMapper> {

    public TagDefManager(DataContext dataContext) {
        super(dataContext, TagDefMapper.class);
    }

}

package net.bodz.lily.schema.meta.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.meta.TagGroupDef;

public class TagGroupDefManager
        extends AbstractEntityManager<TagGroupDef, TagGroupDefMapper> {

    public TagGroupDefManager(DataContext dataContext) {
        super(dataContext, TagGroupDefMapper.class);
    }

}

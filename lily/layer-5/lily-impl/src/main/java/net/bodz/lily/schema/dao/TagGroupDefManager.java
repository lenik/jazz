package net.bodz.lily.schema.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.TagGroupDef;

public class TagGroupDefManager
        extends AbstractEntityManager<TagGroupDef, TagGroupDefMapper> {

    public TagGroupDefManager(DataContext dataContext) {
        super(dataContext, TagGroupDefMapper.class);
    }

}

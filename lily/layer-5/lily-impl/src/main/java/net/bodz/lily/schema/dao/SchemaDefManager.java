package net.bodz.lily.schema.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.SchemaDef;

public class SchemaDefManager
        extends AbstractEntityManager<SchemaDef, SchemaDefMapper> {

    public SchemaDefManager(DataContext dataContext) {
        super(dataContext, SchemaDefMapper.class);
    }

}

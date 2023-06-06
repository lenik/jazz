package net.bodz.lily.schema.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.schema.SchemaDef;

/**
* @label SchemaDef
*/
@ObjectType(SchemaDef.class)
public class SchemaDefIndex
        extends CoIndex<SchemaDef, SchemaDefMask> {

    public SchemaDefIndex() {
    }

}

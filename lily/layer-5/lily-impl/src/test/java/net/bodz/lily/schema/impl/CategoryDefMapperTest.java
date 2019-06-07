package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.CategoryDef;
import net.bodz.lily.schema.CategoryDefSamples;
import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class CategoryDefMapperTest
        extends AbstractMapperTest<CategoryDef, CategoryDefMask, CategoryDefMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public CategoryDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return CategoryDefSamples.build(schema);
    }

}
